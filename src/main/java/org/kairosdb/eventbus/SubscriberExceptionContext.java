/*
 * Copyright (C) 2013 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.kairosdb.eventbus;

import com.google.common.eventbus.EventBus;

import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

/**
 * Context for an exception thrown by a subscriber.
 *
 * @since 16.0
 */
public class SubscriberExceptionContext
{
  private final FilterEventBus eventBus;
  private final Object event;
  private final Object subscriber;
  private final Method subscriberMethod;

  /**
   * @param eventBus The {@link EventBus} that handled the event and the
   *     subscriber. Useful for broadcasting a a new event based on the error.
   * @param event The event object that caused the subscriber to throw.
   * @param subscriber The source subscriber context.
   * @param subscriberMethod the subscribed method.
   */
  SubscriberExceptionContext(FilterEventBus eventBus, Object event, Object subscriber,
      Method subscriberMethod) {
    this.eventBus = requireNonNull(eventBus);
    this.event = requireNonNull(event);
    this.subscriber = requireNonNull(subscriber);
    this.subscriberMethod = requireNonNull(subscriberMethod);
  }

  /**
   * @return The {@link EventBus} that handled the event and the subscriber.
   *     Useful for broadcasting a a new event based on the error.
   */
  public FilterEventBus getEventBus() {
    return eventBus;
  }

  /**
   * @return The event object that caused the subscriber to throw.
   */
  public Object getEvent() {
    return event;
  }

  /**
   * @return The object context that the subscriber was called on.
   */
  public Object getSubscriber() {
    return subscriber;
  }

  /**
   * @return The subscribed method that threw the exception.
   */
  public Method getSubscriberMethod() {
    return subscriberMethod;
  }
}

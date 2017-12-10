/* -*- mode: Java; c-basic-offset: 2; indent-tabs-mode: nil; coding: utf-8-unix -*-
 *
 * Copyright © 2017 MicroBean.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.microbean.freemarker.jandex;

import freemarker.ext.beans.BeansWrapper;

import freemarker.ext.util.ModelFactory;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;

import org.jboss.jandex.IndexView;

/**
 * A {@link ModelFactory} that {@linkplain #create(Object,
 * ObjectWrapper) makes} {@link IndexViewModel} instances.
 *
 * @author <a href="https://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see #create(Object, ObjectWrapper)
 *
 * @see ModelFactory#create(Object, ObjectWrapper)
 */
public class IndexViewModelFactory implements ModelFactory {

  
  /*
   * Constructors.
   */


  /**
   * Creates a new {@link IndexViewModelFactory}.
   */
  public IndexViewModelFactory() {
    super();
  }


  /*
   * Instance methods.
   */
  

  /**
   * Creates a new {@link IndexViewModel} and returns it.
   *
   * <p>This method never returns {@code null}.</p>
   *
   * @param object an {@link IndexView}; must not be {@code null}
   *
   * @param objectWrapper an {@link ObjectWrapper}; must be an
   * instance of {@link BeansWrapper}
   *
   * @return a new {@link IndexViewModel}; never {@code null}
   *
   * @exception IllegalArgumentException if {@code object} is not an
   * instance of {@link IndexView} or if {@code objectWrapper} is not an
   * instance of {@link BeansWrapper}
   *
   * @see ModelFactory#create(Object, ObjectWrapper)
   */
  @Override
  public final TemplateModel create(final Object object, final ObjectWrapper objectWrapper) {    
    if (!(object instanceof IndexView)) {
      throw new IllegalArgumentException("!(object instanceof IndexView): " + object);
    }
    if (!(objectWrapper instanceof BeansWrapper)) {
      throw new IllegalArgumentException("!(objectWrapper instanceof BeansWrapper): " + objectWrapper);
    }
    return new IndexViewModel((BeansWrapper)objectWrapper, (IndexView)object);
  }
  
  
}

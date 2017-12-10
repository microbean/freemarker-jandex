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

import java.util.Objects;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.StringModel;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.AnnotationValue;

/**
 * A {@link BeanModel} that adapts an {@link AnnotationInstance}
 * intelligently.
 *
 * @author <a href="https://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see BeanModel
 *
 * @see AnnotationInstance
 */
public class AnnotationInstanceModel extends BeanModel {

  private final BeansWrapper beansWrapper;
  
  private final AnnotationInstance annotationInstance;
  
  public AnnotationInstanceModel(final BeansWrapper beansWrapper, final AnnotationInstance annotationInstance) {
    super(annotationInstance, beansWrapper);
    Objects.requireNonNull(annotationInstance);
    Objects.requireNonNull(beansWrapper);
    this.annotationInstance = annotationInstance;
    this.beansWrapper = beansWrapper;
  }

  @Override
  public TemplateModel get(final String key) throws TemplateModelException {
    TemplateModel returnValue = null;
    if (key != null) {
      final AnnotationValue value = this.annotationInstance.value(key);
      if (value != null) {
        returnValue = new StringModel(value, this.beansWrapper) {
            @Override
            public final String getAsString() {
              return String.valueOf(value.value());
            }
          };
      }
    }
    if (returnValue == null) {
      returnValue = super.get(key);
    }
    return returnValue;
  }

}

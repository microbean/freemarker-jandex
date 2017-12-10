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

import java.beans.Introspector; // no really

import java.util.Arrays;
import java.util.Objects;

import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

import org.jboss.jandex.DotName;
import org.jboss.jandex.IndexView;

/**
 * A {@link BeanModel} that adapts an {@link IndexView} intelligently.
 *
 * @author <a href="https://about.me/lairdnelson"
 * target="_parent">Laird Nelson</a>
 *
 * @see BeanModel
 *
 * @see IndexView
 */
public class IndexViewModel extends BeanModel {

  private static volatile boolean beanInfoSearchPathSet;
  
  static {
    setBeanInfoSearchPath();
  }

  protected final BeansWrapper beansWrapper;
  
  protected final IndexView indexView;
  
  public IndexViewModel(final BeansWrapper beansWrapper, final IndexView indexView) {
    super(indexView, beansWrapper);
    Objects.requireNonNull(beansWrapper);
    Objects.requireNonNull(indexView);
    this.beansWrapper = beansWrapper;
    this.indexView = indexView;
  }

  @Override
  public TemplateModel get(final String key) throws TemplateModelException {
    TemplateModel returnValue = null;
    if (key != null) {
      final BeansWrapper beansWrapper = this.beansWrapper;
      switch (key) {
      case "allKnownImplementors":
        returnValue = new AllKnownImplementorsModel(beansWrapper, this.indexView);
        break;
      case "allKnownSubclasses":
        returnValue = new AllKnownSubclassesModel(beansWrapper, this.indexView);
        break;
      case "annotations":
        returnValue = new AnnotationsModel(beansWrapper, this.indexView);
        break;
      case "classByName":
        returnValue = new ClassByNameModel(beansWrapper, this.indexView);
        break;
      case "knownClasses":
        returnValue = this.wrap(this.indexView.getKnownClasses());
        break;
      case "knownDirectImplementors":
        returnValue = new KnownDirectImplementorsModel(beansWrapper, this.indexView);
        break;
      case "knownDirectSubclasses":
        returnValue = new KnownDirectSubclassesModel(beansWrapper, this.indexView);
        break;
      default:
        break;
      }
    }
    if (returnValue == null) {
      returnValue = super.get(key);
    }
    return returnValue;
  }

  private static final class AllKnownImplementorsModel extends IndexViewModel {

    private AllKnownImplementorsModel(final BeansWrapper beansWrapper, final IndexView indexView) {
      super(beansWrapper, indexView);
    }

    @Override
    public final TemplateModel get(final String key) throws TemplateModelException {
      TemplateModel returnValue = null;
      if (key != null) {
        returnValue = this.wrap(this.indexView.getAllKnownImplementors(DotName.createSimple(key)));
      }
      return returnValue;
    }
    
  }

  private static final class AllKnownSubclassesModel extends IndexViewModel {

    private AllKnownSubclassesModel(final BeansWrapper beansWrapper, final IndexView indexView) {
      super(beansWrapper, indexView);
    }

    @Override
    public final TemplateModel get(final String key) throws TemplateModelException {
      TemplateModel returnValue = null;
      if (key != null) {
        returnValue = this.wrap(this.indexView.getAllKnownSubclasses(DotName.createSimple(key)));
      }
      return returnValue;
    }
    
  }

  private static final class AnnotationsModel extends IndexViewModel {

    private AnnotationsModel(final BeansWrapper beansWrapper, final IndexView indexView) {
      super(beansWrapper, indexView);
    }

    @Override
    public final TemplateModel get(final String key) throws TemplateModelException {
      TemplateModel returnValue = null;
      if (key != null) {
        returnValue = this.wrap(this.indexView.getAnnotations(DotName.createSimple(key)));
      }
      return returnValue;
    }
    
  }

  private static final class ClassByNameModel extends IndexViewModel {

    private ClassByNameModel(final BeansWrapper beansWrapper, final IndexView indexView) {
      super(beansWrapper, indexView);
    }

    @Override
    public final TemplateModel get(final String key) throws TemplateModelException {
      TemplateModel returnValue = null;
      if (key != null) {
        returnValue = this.wrap(this.indexView.getClassByName(DotName.createSimple(key)));
      }
      return returnValue;
    }
    
  }

  private static final class KnownDirectImplementorsModel extends IndexViewModel {

    private KnownDirectImplementorsModel(final BeansWrapper beansWrapper, final IndexView indexView) {
      super(beansWrapper, indexView);
    }

    @Override
    public final TemplateModel get(final String key) throws TemplateModelException {
      TemplateModel returnValue = null;
      if (key != null) {
        returnValue = this.wrap(this.indexView.getKnownDirectImplementors(DotName.createSimple(key)));
      }
      return returnValue;
    }
    
  }

  private static final class KnownDirectSubclassesModel extends IndexViewModel {

    private KnownDirectSubclassesModel(final BeansWrapper beansWrapper, final IndexView indexView) {
      super(beansWrapper, indexView);
    }

    @Override
    public final TemplateModel get(final String key) throws TemplateModelException {
      TemplateModel returnValue = null;
      if (key != null) {
        returnValue = this.wrap(this.indexView.getKnownDirectSubclasses(DotName.createSimple(key)));
      }
      return returnValue;
    }
    
  }

  
  /*
   * Static methods.
   */

  
  static final void setBeanInfoSearchPath() {
    synchronized (Introspector.class) {
      if (!beanInfoSearchPathSet) {
        final String[] beanInfoSearchPath = Introspector.getBeanInfoSearchPath();
        final String[] newBeanInfoSearchPath;
        if (beanInfoSearchPath == null || beanInfoSearchPath.length <= 0) {
          newBeanInfoSearchPath = new String[1];
        } else {
          newBeanInfoSearchPath = Arrays.copyOf(beanInfoSearchPath, beanInfoSearchPath.length + 1);
          assert newBeanInfoSearchPath != null;
          assert newBeanInfoSearchPath.length > beanInfoSearchPath.length;
        }
        newBeanInfoSearchPath[newBeanInfoSearchPath.length - 1] = "org.microbean.freemarker.jandex.beaninfo";
        Introspector.setBeanInfoSearchPath(newBeanInfoSearchPath);
        beanInfoSearchPathSet = true;
      }
    }
  }
  
}

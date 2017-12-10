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

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import freemarker.template.Configuration;
import freemarker.template.Version;

import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestIndexViewModel {

  private static final Set<String> ANNOTATION_INSTANCE_PROPERTY_DESCRIPTOR_NAMES =
    new TreeSet<>(Arrays.asList("class", "name", "target", "value", "values"));
  
  
  private static final Set<String> CLASS_INFO_PROPERTY_DESCRIPTOR_NAMES =
    new TreeSet<>(Arrays.asList("class", "classAnnotations", "classFacet",
                                "enclosingClass", "enclosingMethod",
                                "fieldFacet", "fields", "flags",
                                "hasNoArgsConstructor", "interfaceNames",
                                "interfaceTypes", "kind", "methodFacet",
                                "methodParameterFacet",
                                "methods", "name", "nestingType", "simpleName",
                                "superClassType", "superName", "typeFacet",
                                "typeParameters"));

  private static final Set<String> DOT_NAME_PROPERTY_DESCRIPTOR_NAMES =
    new TreeSet<>(Arrays.asList("class", "componentized", "local", "prefix"));
  
  /**
   * A {@link Version} representing the version of <a
   * href="http://freemarker.org/">Freemarker</a> in use.
   *
   * <p>This field is never {@code null}.</p>
   */
  static final Version FREEMARKER_VERSION = Configuration.VERSION_2_3_23;

  private Configuration configuration;
  
  public TestIndexViewModel() {
    super();
  }

  @Before
  public void setUp() {
    IndexViewModel.setBeanInfoSearchPath();
    final Configuration configuration = new Configuration(FREEMARKER_VERSION);
    configuration.setDefaultEncoding("UTF-8");
    configuration.setAPIBuiltinEnabled(true);
    this.configuration = configuration;
  }

  @Test
  public void testBeanInfoStuff() {
    IndexViewModel.setBeanInfoSearchPath();
    assertBeanInfoSearchPathIsRight();
  }

  private static final void assertBeanInfoSearchPathIsRight() {
    final String[] beanInfoSearchPath = Introspector.getBeanInfoSearchPath();
    assertNotNull(beanInfoSearchPath);
    final List<String> beanInfoSearchPathList = Arrays.asList(beanInfoSearchPath);
    assertNotNull(beanInfoSearchPathList);
    assertFalse(beanInfoSearchPathList.isEmpty());
    assertEquals("org.microbean.freemarker.jandex.beaninfo", beanInfoSearchPathList.get(beanInfoSearchPathList.size() - 1));
    assertEquals(beanInfoSearchPathList.indexOf("org.microbean.freemarker.jandex.beaninfo"),
                 beanInfoSearchPathList.lastIndexOf("org.microbean.freemarker.jandex.beaninfo"));
  }
  
  @Test
  public void testThatBeanInfosArePickedUp() throws IntrospectionException {
    assertBeanInfoSearchPathIsRight();
    assertPropertyDescriptors(AnnotationInstance.class, ANNOTATION_INSTANCE_PROPERTY_DESCRIPTOR_NAMES);
    // assertMethodDescriptors(AnnotationInstance.class, Collections.emptySet());
    assertPropertyDescriptors(ClassInfo.class, CLASS_INFO_PROPERTY_DESCRIPTOR_NAMES);
    assertPropertyDescriptors(DotName.class, DOT_NAME_PROPERTY_DESCRIPTOR_NAMES);    
  }

  private static final void assertMethodDescriptors(final Class<?> beanClass, final Set<? extends String> expectedNames) throws IntrospectionException {
    assertNotNull(beanClass);
    assertMethodDescriptors(Introspector.getBeanInfo(beanClass), expectedNames);
  }
  
  private static final void assertMethodDescriptors(final BeanInfo beanInfo, final Set<? extends String> expectedNames) {
    assertNotNull(beanInfo);
    assertFeatureDescriptors(beanInfo.getMethodDescriptors(), expectedNames);
  }
  
  private static final void assertPropertyDescriptors(final Class<?> beanClass, final Set<? extends String> expectedNames) throws IntrospectionException {
    assertNotNull(beanClass);
    assertPropertyDescriptors(Introspector.getBeanInfo(beanClass), expectedNames);
  }
  
  private static final void assertPropertyDescriptors(final BeanInfo beanInfo, final Set<? extends String> expectedNames) {
    assertNotNull(beanInfo);
    assertFeatureDescriptors(beanInfo.getPropertyDescriptors(), expectedNames);
  }
  
  private static final void assertFeatureDescriptors(final FeatureDescriptor[] fds, final Set<? extends String> expectedNames) {
    assertNotNull(fds);
    assertNotNull(expectedNames);
    assertEquals(expectedNames.size(), fds.length);
    for (final FeatureDescriptor fd : fds) {
      assertNotNull(fd);
      assertTrue(expectedNames.contains(fd.getName()));
    }
  }

}

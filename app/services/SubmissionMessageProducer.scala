/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package services

import java.io.StringWriter

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.{JacksonXmlModule, XmlMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import domain.declaration._

import scala.xml.{Elem, XML}

trait SubmissionMessageProducer {

  private val module = new JacksonXmlModule()
  module.setDefaultUseWrapper(false)
  private val mapper = new XmlMapper(module)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
  mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
  mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT)
  mapper.registerModule(DefaultScalaModule)

  private[services] def produceDeclarationMessage(metaData: MetaData): Elem = {
    val sw = new StringWriter()
    mapper.writeValue(sw, metaData)
    XML.loadString(sw.toString)
  }

}

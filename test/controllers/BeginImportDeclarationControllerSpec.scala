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

package controllers

import play.api.http.Status
import play.api.test.Helpers._
import uk.gov.hmrc.customs.test.ControllerSpec

class BeginImportDeclarationControllerSpec extends ControllerSpec {

  val method = "GET"
  val uri = uriWithContextPath("/begin")

  s"$method $uri" should {

    "return 200" in requestScenario(method, uri) { resp =>
      status(resp) must be (Status.OK)
    }

    "return HTML" in requestScenario(method, uri) { resp =>
      contentType(resp) must be (Some("text/html"))
      charset(resp) must be (Some("utf-8"))
    }

    "display message" in requestScenario(method, uri) { resp =>
      contentAsString(resp).asBodyFragment should include element withClass("message").withValue("Well done. You have begun your first step on a long journey.")
    }

  }

}
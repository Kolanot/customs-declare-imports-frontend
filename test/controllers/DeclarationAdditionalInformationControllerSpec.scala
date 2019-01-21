/*
 * Copyright 2019 HM Revenue & Customs
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

import domain.auth.{EORI, SignedInUser}
import forms.DeclarationFormMapping._
import generators.Generators
import org.mockito.ArgumentMatchers.{eq => eqTo, _}
import org.mockito.Mockito._
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen._
import org.scalatest.OptionValues
import org.scalatest.prop.PropertyChecks
import play.api.data.Form
import play.api.test.Helpers._
import services.cachekeys.AdditionalInformationId
import uk.gov.hmrc.customs.test.IntegrationTest
import uk.gov.hmrc.customs.test.behaviours._
import uk.gov.hmrc.wco.dec.AdditionalInformation
import views.html.declaration_additional_information

import scala.concurrent.Future

class DeclarationAdditionalInformationControllerSpec extends CustomsSpec
  with PropertyChecks
  with Generators
  with OptionValues {

  def form = Form(additionalInformationMapping)
  def controller(user: Option[SignedInUser]) =
    new DeclarationAdditionalInformationController(new FakeActions(user), mockCustomsCacheService)

  def view(form: Form[AdditionalInformation], additionalInformation: List[AdditionalInformation]): String =
    declaration_additional_information(form, additionalInformation)(fakeRequest, messages, appConfig).body

  ".onPageLoad" should {

    "return OK" when {

      "user is signed in" in {

        forAll { user: SignedInUser =>

          when(mockCustomsCacheService.getByKey[Seq[AdditionalInformation]](any(), any())(any(), any(), any()))
            .thenReturn(Future.successful(None))

          val result = controller(Some(user)).onPageLoad()(fakeRequest)

          status(result) mustBe OK
          contentAsString(result) mustBe view(form, List.empty)
        }
      }
    }

    "return Unauthorized" when {

      "user has no eori" in {

        forAll { user: UnauthenticatedUser =>

          whenever(user.user.eori.isEmpty) {

            val result = controller(Some(user.user)).onPageLoad()(fakeRequest)

            status(result) mustBe UNAUTHORIZED
          }
        }
      }
    }

    "load data from cache" in {

      val gen = option(listOf(arbitrary[AdditionalInformation]))

      forAll(arbitrary[SignedInUser], gen) {
        case (user, data) =>

          when(mockCustomsCacheService.getByKey(eqTo(EORI(user.eori.value)), eqTo(AdditionalInformationId.declaration))(any(), any(), any()))
            .thenReturn(Future.successful(data))

          val result = controller(Some(user)).onPageLoad()(fakeRequest)

          status(result) mustBe OK
          contentAsString(result) mustBe view(form, data.getOrElse(List.empty))
      }
    }

    "integrate" when {

      val url = "/submit-declaration-goods/declaration-additional-information"

      "return 200" in new IntegrationTest {
        withCaching(None)
        withSignedInUser() { (headers, session, tags) =>
          withRequest(GET, uriWithContextPath(url), headers, session, tags) {
            wasOk
          }
        }
      }

      "requires authentication" in new IntegrationTest {
        withoutSignedInUser() {
          withRequest(GET, uriWithContextPath(url)) { resp =>
            wasRedirected(ggLoginRedirectUri(uriWithContextPath(url)), resp)
          }
        }
      }
    }
  }
}
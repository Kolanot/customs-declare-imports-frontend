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

import domain.features.Feature
import play.api.test.Helpers._
import uk.gov.hmrc.customs.test.assertions.{HtmlAssertions, HttpAssertions}
import uk.gov.hmrc.customs.test.behaviours._

class GovernmentAgencyGoodsItemsControllerSpec extends CustomsSpec
  with AuthenticationBehaviours
  with FeatureBehaviours
  with RequestHandlerBehaviours
  with HttpAssertions
  with HtmlAssertions {

  val goodsItemsListUri = uriWithContextPath("/submit-declaration-goods/gov-agency-goods-items")
  val goodsItemsPageUri = uriWithContextPath("/submit-declaration-goods/goods-item-value")
  val get = "GET"
  val postMethod = "POST"

  "GovernmentAgencyGoodsItemsController" should {

    "require Authentication" in withFeatures((enabled(Feature.submit))) {
      withoutSignedInUser() {
        withRequest("GET", goodsItemsListUri) { resp =>
          wasRedirected(ggLoginRedirectUri(goodsItemsListUri), resp)
        }
      }
    }

    " return 200" in withFeatures((enabled(Feature.submit))) {
      withCaching(None)
      withSignedInUser() { (headers, session, tags) =>
        withRequest("GET", goodsItemsListUri, headers, session, tags) {
          wasOk
        }
      }
    }

    "display GoodsItems Page with list of items entered displayed" in withFeatures((enabled(Feature.submit))) {
      withSignedInUser() { (headers, session, tags) =>
        withCaching(None)
        withRequest("GET", goodsItemsListUri, headers, session, tags) { resp =>
          val content = contentAsHtml(resp)
          content should include element withValue("No Goods Shipments available")

        }
      }
    }

    "display GoodsItems values with single field Page" in withFeatures((enabled(Feature.submit))) {
      withSignedInUser() { (headers, session, tags) =>
        withCaching(None)
        withRequest("GET", goodsItemsPageUri, headers, session, tags) { resp =>
          val content = contentAsString(resp)
          content must include("customsValueAmount")
          content must include("statisticalValueAmount.currencyId")
          content must include("statisticalValueAmount.value")
          content must include("transactionNatureCode")
          content must include("destination.countryCode")
          content must include("destination.regionId")
          content must include("ucr.id")
          content must include("ucr.traderAssignedReferenceId")
          content must include("exportCountry.id")
          content must include("valuationAdjustment.additionCode")
        }
      }
    }


    val invalidPayload = Map(
      "customsValueAmount" -> "-0",
      "statisticalValueAmount.currencyId" -> "name1nasdfghlertghoy asdflgothidlglfdleasdflksdf",
      "statisticalValueAmount.value" -> "Address1 Address1 Address1 Address1 Address1 Address1 Address1 Address1",
      "transactionNatureCode" -> "12345678912341234",
      "destination.countryCode" -> "references",
      "destination.regionId" -> "ID1234567",
      "ucr.id" -> "ID1234567",
      "ucr.traderAssignedReferenceId" -> "ID1234567",
      "exportCountry.id" -> "ID1234567",
      "valuationAdjustment.additionCode" -> "ID1234567",
      "submit" -> "Add"
    )

    //TODO random validations tested should revisit
    "validate user input data on click of Add" in withFeatures(enabled(Feature.submit)) {
      withSignedInUser() { (headers, session, tags) =>
        withRequestAndFormBody(postMethod, goodsItemsPageUri, headers, session, tags, invalidPayload) { resp =>
          val stringResult = contentAsString(resp)
          stringResult must include("customs Value Amount must not be negative")
          stringResult must include("id=\"error-message-sequenceNumeric-input\">This field is required")
          stringResult must include("Real number value expected")
          stringResult must include("id=\"error-message-statisticalValueAmount_currencyId-input\">currencyId is only 3 characters")
          stringResult must include("valuationAdjustment should be less than or equal to 4 characters")
          stringResult must include("export Country code should be less than or equal to 2 characters")
          stringResult must include("country code is only 3 characters")
          stringResult must include("id=\"error-message-transactionNatureCode-input\">Numeric value expected")
        }
      }
    }

    val validPayload = Map(
      "customsValueAmount" -> "30.00",
      "sequenceNumeric" -> "3",
      "statisticalValueAmount.currencyId" -> "UK",
      "statisticalValueAmount.value" -> "3345",
      "transactionNatureCode" -> "123",
      "destination.countryCode" -> "UK",
      "destination.regionId" -> "UK",
      "ucr.id" -> "ID1",
      "ucr.traderAssignedReferenceId" -> "TRADER_REF-1",
      "exportCountry.id" -> "PL",
      "valuationAdjustment.additionCode" -> "DDC",
      "submit" -> "Add"
    )

    "navigate to good items page on click of Continue " in withFeatures(enabled(Feature.submit)) {
      withSignedInUser() { (headers, session, tags) =>
        withCaching(None)
        withRequestAndFormBody(postMethod, goodsItemsPageUri, headers, session, tags, validPayload) { resp =>
          val header = resp.futureValue.header
          status(resp) must be(SEE_OTHER)
          header.headers.get("Location") must
            be(Some("/customs-declare-imports/submit-declaration-goods/add-gov-agency-goods-item"))
        }
      }
    }
  }

}

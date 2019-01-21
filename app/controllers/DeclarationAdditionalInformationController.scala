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

import forms.DeclarationFormMapping._
import com.google.inject.Inject
import config.AppConfig
import play.api.data.Form
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, AnyContent}
import services.CustomsCacheService
import uk.gov.hmrc.play.bootstrap.controller.FrontendController
import views.html.declaration_additional_information
import domain.DeclarationFormats._
import services.cachekeys.AdditionalInformationId

class DeclarationAdditionalInformationController @Inject()(actions: Actions, cacheService: CustomsCacheService)
                                                          (implicit val messagesApi: MessagesApi, appConfig: AppConfig)
  extends FrontendController with I18nSupport {

  lazy val form = Form(additionalInformationMapping)

  def onPageLoad(): Action[AnyContent] = (actions.auth andThen actions.eori).async {
    implicit request =>
      cacheService.getByKey(request.eori, AdditionalInformationId.declaration).map {
        additionalInformation =>
          Ok(declaration_additional_information(form, additionalInformation.getOrElse(Seq.empty)))
      }
  }
}

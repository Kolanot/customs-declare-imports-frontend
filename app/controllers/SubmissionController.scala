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

import config.AppConfig
import domain.declaration._
import domain.features.Feature
import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, AnyContent}
import services.CustomsDeclarationsConnector
import uk.gov.hmrc.play.bootstrap.controller.FrontendController

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SubmissionController @Inject()(actions: Actions, client: CustomsDeclarationsConnector, val messagesApi: MessagesApi)(implicit val appConfig: AppConfig, ec: ExecutionContext) extends FrontendController with I18nSupport {

  private val dateTimePattern = "(20\\d{6})(\\d{6}(Z|[-+]\\d\\d))?"
  private val dateTimePatternErrorMessage = "Date time string does not match required pattern"
  private val dateTimeFormatCodes = Set("102", "304")
  private val dateTimeFormatCodeErrorMessage = "Unknown format code. Must be either '102' or '304'"

  val declarationForm: Form[AllInOneForm] = Form(
    mapping(
      "badgeId" -> optional(text),
      "metaData" -> mapping(
        "wcoDataModelVersionCode" -> optional(text),
        "wcoTypeName" -> optional(text),
        "responsibleCountryCode" -> optional(text),
        "responsibleAgencyName" -> optional(text),
        "agencyAssignedCustomizationCode" -> optional(text),
        "agencyAssignedCustomizationVersionCode" -> optional(text),
        "declaration" -> mapping(
          "acceptanceDateTime" -> optional(text(maxLength = 35).verifying(dateTimePatternErrorMessage, _.matches(dateTimePattern))),
          "acceptanceDateTimeFormatCode" -> optional(text.verifying(dateTimeFormatCodeErrorMessage, dateTimeFormatCodes.contains(_))),
          "functionCode" -> optional(text.verifying("Unknown function code. Must be one of 9, 13, or 14", Set("9", "13", "14").contains(_))),
          "functionalReferenceId" -> optional(text(maxLength = 35)),
          "id" -> optional(text(maxLength = 70)),
          "issueDateTime" -> optional(text(maxLength = 35).verifying(dateTimePatternErrorMessage, _.matches(dateTimePattern))),
          "issueDateTimeFormatCode" -> optional(text.verifying(dateTimeFormatCodeErrorMessage, dateTimeFormatCodes.contains(_))),
          "issueLocationId" -> optional(text(maxLength = 5)),
          "typeCode" -> optional(text(maxLength = 3)),
          "goodsItemQuantity" -> optional(number(min = 0, max = 99999)),
          "declarationOfficeId" -> optional(text(maxLength = 17)),
          "invoiceAmount" -> optional(bigDecimal(precision = 16, scale = 3)),
          "invoiceAmountCurrencyId" -> optional(text(maxLength = 3)),
          "loadingListQuantity" -> optional(number(min = 0, max = 99999)),
          "totalGrossMassMeasure" -> optional(bigDecimal(precision = 16, scale = 6)),
          "totalGrossMassMeasureUnitCode" -> optional(text(maxLength = 5)),
          "totalPackageQuantity" -> optional(number(min = 0, max = 99999999)),
          "specificCircumstancesCodeCode" -> optional(text(maxLength = 3)),
          "authentication" -> mapping(
            "authentication" -> optional(text(maxLength = 256)),
            "authenticatorName" -> optional(text(maxLength = 70))
          )(AuthenticationForm.apply)(AuthenticationForm.unapply),
          "submitter" -> mapping(
            "name" -> optional(text(maxLength = 70)),
            "id" -> optional(text(maxLength = 17)),
            "address" -> mapping(
              "cityName" -> optional(text(maxLength = 35)),
              "countryCode" -> optional(text(minLength = 2, maxLength = 2)),
              "countrySubDivisionCode" -> optional(text(maxLength = 9)),
              "countrySubDivisionName" -> optional(text(maxLength = 35)),
              "line" -> optional(text(maxLength = 70)),
              "postcodeId" -> optional(text(maxLength = 9))
            )(AddressForm.apply)(AddressForm.unapply)
          )(SubmitterForm.apply)(SubmitterForm.unapply),
          "additionalDocument" -> mapping(
            "id" -> optional(text(maxLength = 70)),
            "categoryCode" -> optional(text(maxLength = 3)),
            "typeCode" -> optional(text(maxLength = 3))
          )(DeclarationAdditionalDocumentForm.apply)(DeclarationAdditionalDocumentForm.unapply),
          "hack" -> mapping(
            "additionalInformation" -> mapping(
              "statementCode" -> optional(text(maxLength = 17)),
              "statementDescription" -> optional(text(maxLength = 512)),
              "statementTypeCode" -> optional(text(maxLength = 3)),
              "pointer" -> mapping(
                "sequenceNumeric" -> optional(number(min = 0, max = 99999)),
                "documentSectionCode" -> optional(text(maxLength = 3)),
                "tagId" -> optional(text(maxLength = 4))
              )(PointerForm.apply)(PointerForm.unapply)
            )(AdditionalInformationForm.apply)(AdditionalInformationForm.unapply),
            "agent" -> mapping(
              "name" -> optional(text(maxLength = 70)),
              "id" -> optional(text(maxLength = 17)),
              "functionCode" -> optional(text(maxLength = 3)),
              "address" -> mapping(
                "cityName" -> optional(text(maxLength = 35)),
                "countryCode" -> optional(text(minLength = 2, maxLength = 2)),
                "countrySubDivisionCode" -> optional(text(maxLength = 9)),
                "countrySubDivisionName" -> optional(text(maxLength = 35)),
                "line" -> optional(text(maxLength = 70)),
                "postcodeId" -> optional(text(maxLength = 9))
              )(AddressForm.apply)(AddressForm.unapply)
            )(AgentForm.apply)(AgentForm.unapply),
            "authorisationHolder" -> mapping(
              "id" -> optional(text(maxLength = 17)),
              "categoryCode" -> optional(text(maxLength = 4))
            )(AuthorisationHolderForm.apply)(AuthorisationHolderForm.unapply)
          )(MassiveHackToCreateHugeForm.apply)(MassiveHackToCreateHugeForm.unapply)
        )(DeclarationForm.apply)(DeclarationForm.unapply).verifying("Acceptance Date Time Format Code must be specified when Acceptance Date Time is provided", form => {
          form.acceptanceDateTime.isEmpty || (form.acceptanceDateTime.isDefined && form.acceptanceDateTimeFormatCode.isDefined)
        }).verifying("Issue Date Time Format Code must be specified when Issue Date Time is provided", form => {
          form.issueDateTime.isEmpty || (form.issueDateTime.isDefined && form.issueDateTimeFormatCode.isDefined)
        })
      )(MetaDataForm.apply)(MetaDataForm.unapply)
    )(AllInOneForm.apply)(AllInOneForm.unapply)
  )

  def showDeclarationForm: Action[AnyContent] = (actions.switch(Feature.declaration) andThen actions.auth).async { implicit req =>
    Future.successful(Ok(views.html.declaration_form(declarationForm)))
  }

  def handleDeclarationForm: Action[AnyContent] = (actions.switch(Feature.declaration) andThen actions.auth).async { implicit req =>
    val bound = declarationForm.bindFromRequest()
    bound.fold(
      errors => Future.successful(BadRequest(views.html.declaration_form(errors))),
      success => {
        client.submitImportDeclaration(success.toMetaData, success.badgeId).map { b =>
          Ok(views.html.declaration_acknowledgement(b))
        }
      }
    )
  }

}

case class AuthorisationHolderForm(id: Option[String] = None,
                                   categoryCode: Option[String] = None) {

  def toAuthorisationHolder: Option[AuthorisationHolder] = if (anyDefined) Some(AuthorisationHolder(
    id, categoryCode
  )) else None

  private def anyDefined: Boolean = id.isDefined || categoryCode.isDefined

}

case class AllInOneForm(badgeId: Option[String] = None,
                        metaData: MetaDataForm = MetaDataForm()) {

  def toMetaData: MetaData = metaData.toMetaData

}

case class MetaDataForm(wcoDataModelVersionCode: Option[String] = None,
                        wcoTypeName: Option[String] = None,
                        responsibleCountryCode: Option[String] = None,
                        responsibleAgencyName: Option[String] = None,
                        agencyAssignedCustomizationCode: Option[String] = None,
                        agencyAssignedCustomizationVersionCode: Option[String] = None,
                        declaration: DeclarationForm = DeclarationForm()) {

  def toMetaData: MetaData = MetaData(
    declaration.toDeclaration,
    wcoDataModelVersionCode,
    wcoTypeName,
    responsibleCountryCode,
    responsibleAgencyName,
    agencyAssignedCustomizationCode,
    agencyAssignedCustomizationVersionCode
  )

}

// At present, our form mirrors the declaration XML exactly. Later this may change. Therefore, it is probably useful
// to retain a distinction between view model class and XML model class and map the former to the latter
case class DeclarationForm(acceptanceDateTime: Option[String] = None,
                           acceptanceDateTimeFormatCode: Option[String] = None,
                           functionCode: Option[String] = None,
                           functionalReferenceId: Option[String] = None,
                           id: Option[String] = None,
                           issueDateTime: Option[String] = None,
                           issueDateTimeFormatCode: Option[String] = None,
                           issueLocationId: Option[String] = None,
                           typeCode: Option[String] = None,
                           goodsItemQuantity: Option[Int] = None,
                           declarationOfficeId: Option[String] = None,
                           invoiceAmount: Option[BigDecimal] = None,
                           invoiceAmountCurrencyId: Option[String] = None,
                           loadingListQuantity: Option[Int] = None,
                           totalGrossMassMeasure: Option[BigDecimal] = None,
                           totalGrossMassMeasureUnitCode: Option[String] = None,
                           totalPackageQuantity: Option[Int] = None,
                           specificCircumstancesCodeCode: Option[String] = None,
                           authentication: AuthenticationForm = AuthenticationForm(),
                           submitter: SubmitterForm = SubmitterForm(),
                           additionalDocument: DeclarationAdditionalDocumentForm = DeclarationAdditionalDocumentForm(),
                           hack: MassiveHackToCreateHugeForm = MassiveHackToCreateHugeForm()) {

  private val defaultDateTimeFormatCode = "304"

  def toDeclaration: Declaration = Declaration(
    acceptanceDateTime = acceptanceDateTime.map(dt => AcceptanceDateTime(DateTimeString(acceptanceDateTimeFormatCode.getOrElse(defaultDateTimeFormatCode), dt))),
    functionCode = functionCode,
    functionalReferenceId = functionalReferenceId,
    id = id,
    issueDateTime = issueDateTime.map(dt => IssueDateTime(DateTimeString(issueDateTimeFormatCode.getOrElse(defaultDateTimeFormatCode), dt))),
    issueLocationId = issueLocationId,
    typeCode = typeCode,
    goodsItemQuantity = goodsItemQuantity,
    declarationOfficeId = declarationOfficeId,
    invoiceAmount = invoiceAmount.map(InvoiceAmount(_, invoiceAmountCurrencyId)),
    loadingListQuantity = loadingListQuantity,
    totalGrossMassMeasure = totalGrossMassMeasure.map(MassMeasure(_, totalGrossMassMeasureUnitCode)),
    totalPackageQuantity = totalPackageQuantity,
    specificCircumstancesCodeCode = specificCircumstancesCodeCode,
    authentication = authentication.toAuthentication,
    additionalDocuments = additionalDocument.toAdditionalDocument.toSeq,
    additionalInformations = hack.additionalInformation.toAdditionalInformation.toSeq,
    agent = hack.agent.toAgent,
    authorisationHolders = hack.authorisationHolder.toAuthorisationHolder.toSeq
  )

}

case class AuthenticationForm(authentication: Option[String] = None,
                              authenticatorName: Option[String] = None) {

  def toAuthentication: Option[Authentication] = (authentication, authenticatorName) match {
    case (Some(auth), Some(name)) => Some(Authentication(Some(auth), Some(Authenticator(Some(name)))))
    case (Some(auth), None) => Some(Authentication(Some(auth)))
    case (None, Some(name)) => Some(Authentication(None, Some(Authenticator(Some(name)))))
    case _ => None
  }

}

case class SubmitterForm(name: Option[String] = None,
                         id: Option[String] = None,
                         address: AddressForm = AddressForm()) {

  def toSubmitter: Submitter = Submitter(
    name = name,
    id = id,
    address = address.toAddress
  )

}

case class AddressForm(cityName: Option[String] = None,
                       countryCode: Option[String] = None,
                       countrySubDivisionCode: Option[String] = None,
                       countrySubDivisionName: Option[String] = None,
                       line: Option[String] = None,
                       postcodeId: Option[String] = None) {

  def toAddress: Option[Address] = if (anyDefined) Some(Address(
    cityName, countryCode, countrySubDivisionCode, countrySubDivisionName, line, postcodeId
  )) else None

  private def anyDefined: Boolean = cityName.isDefined ||
    countryCode.isDefined ||
    countrySubDivisionCode.isDefined ||
    countrySubDivisionName.isDefined ||
    line.isDefined ||
    postcodeId.isDefined

}

case class DeclarationAdditionalDocumentForm(id: Option[String] = None, // max 70 chars
                                             categoryCode: Option[String] = None, // max 3 chars
                                             typeCode: Option[String] = None) { // max 3 chars

  def toAdditionalDocument: Option[AdditionalDocument] = if (anyDefined) Some(AdditionalDocument(
    id, categoryCode, typeCode
  )) else None

  private def anyDefined: Boolean = id.isDefined ||
    categoryCode.isDefined ||
    typeCode.isDefined

}

case class MassiveHackToCreateHugeForm(additionalInformation: AdditionalInformationForm = AdditionalInformationForm(),
                                       agent: AgentForm = AgentForm(),
                                       authorisationHolder: AuthorisationHolderForm = AuthorisationHolderForm())

case class AdditionalInformationForm(statementCode: Option[String] = None,
                                     statementDescription: Option[String] = None,
                                     statementTypeCode: Option[String] = None,
                                     pointer: PointerForm = PointerForm()) {

  def toAdditionalInformation: Option[AdditionalInformation] = if (anyDefined) Some(AdditionalInformation(
    statementCode, statementDescription, statementTypeCode, pointer.toPointer.toSeq
  )) else None

  private def anyDefined: Boolean = statementCode.isDefined ||
    statementDescription.isDefined ||
    statementTypeCode.isDefined ||
    pointer.toPointer.isDefined

}

case class PointerForm(sequenceNumeric: Option[Int] = None,
                       documentSectionCode: Option[String] = None,
                       tagId: Option[String] = None) {

  def toPointer: Option[Pointer] = if (anyDefined) Some(Pointer(
    sequenceNumeric, documentSectionCode, tagId
  )) else None

  private def anyDefined: Boolean = sequenceNumeric.isDefined ||
    documentSectionCode.isDefined ||
    tagId.isDefined

}

case class AgentForm(name: Option[String] = None,
                     id: Option[String] = None,
                     functionCode: Option[String] = None,
                     address: AddressForm = AddressForm()) {

  def toAgent: Option[Agent] = if (anyDefined) Some(Agent(
    name, id, functionCode, address.toAddress
  )) else None

  private def anyDefined: Boolean = name.isDefined ||
    id.isDefined ||
    functionCode.isDefined ||
    address.toAddress.isDefined

}

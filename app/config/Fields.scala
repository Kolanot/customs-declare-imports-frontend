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

package config

import scala.reflect.runtime.universe
import scala.reflect.runtime.universe._

class Fields extends Options {

  // declarant details form fields
  val declarantName: TextInput = TextInput(
    name = "declaration.declarant.name",
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val declarantAddressLine: TextInput = TextInput(
    name = "declaration.declarant.address.line",
    labelKey = Some("common.fields.address.line"),
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val declarantAddressCityName: TextInput = TextInput(
    name = "declaration.declarant.address.cityName",
    labelKey = Some("common.fields.address.cityName"),
    validators = Seq(OptionalAlphaNumericValidator(30))
  )
  val declarantAddressCountryCode: SelectInput = SelectInput(
    name = "declaration.declarant.address.countryCode",
    options = countryTypes,
    labelKey = Some("common.fields.address.countryCode"),
    validators = Seq(OptionalContainsValidator(countryTypes.map(_._1).toSet))
  )
  val declarantAddressPostcode: TextInput = TextInput(
    name = "declaration.declarant.address.postcodeId",
    labelKey = Some("common.fields.address.postcodeId"),
    validators = Seq(OptionalAlphaNumericValidator(9))
  )
  val declarantEori: TextInput = TextInput(
    name = "declaration.declarant.id",
    labelKey = Some("common.fields.eori"),
    hintKey = Some("common.hints.eori"),
    validators = Seq(RequiredAlphaNumericValidator(17, 17))
  )

  // references form fields
  // TODO governmentAgencyGoodsItems is a seq of elements needs mapping the sequence
  val referenceNumberUcr1: TextInput = TextInput(
    name = "declaration.goodsShipment.ucr.traderAssignedReferenceId",
    validators = Seq(OptionalAlphaNumericValidator(35))
  )
  val declarantFunctionalReferenceId: TextInput = TextInput(
    name = "declaration.functionalReferenceId",
    hintKey = Some("declaration.functionalReferenceId.hint"),
    validators = Seq(RequiredAlphaNumericValidator(22))
  )
  val declarationType: RadioInput = RadioInput(
    name = "declaration.typeCode",
    options = declarationTypes
  )
  val additionalDeclaratonType: RadioInput = RadioInput(
    name = "declaration.typeCode.additional", // FIXME same xml element mapped for declarationType and additionalDeclarationType - have had to use fictional property
    options = additionalDeclarationTypes,
    labelKey = Some("declaration.typeCode.additional")
  )

  // exporter details form fields
  val exporterName: TextInput = TextInput(
    name = "declaration.exporter.name",
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val exporterAddressLine: TextInput = TextInput(
    name = "declaration.exporter.address.line",
    labelKey = Some("common.fields.address.line"),
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val exporterAddressCityName: TextInput = TextInput(
    name = "declaration.exporter.address.cityName",
    labelKey = Some("common.fields.address.cityName"),
    validators = Seq(OptionalAlphaNumericValidator(30))
  )
  val exporterAddressCountryCode: SelectInput = SelectInput(
    name = "declaration.exporter.address.countryCode",
    options = countryTypes,
    labelKey = Some("common.fields.address.countryCode"),
    validators = Seq(OptionalContainsValidator(countryTypes.map(_._1).toSet))
  )
  val exporterAddressPostcode: TextInput = TextInput(
    name = "declaration.exporter.address.postcodeId",
    labelKey = Some("common.fields.address.postcodeId"),
    validators = Seq(OptionalAlphaNumericValidator(9))
  )
  val exporterEori: TextInput = TextInput(
    name = "declaration.exporter.id",
    labelKey = Some("common.fields.eori"),
    hintKey = Some("common.hints.eori"),
    validators = Seq(OptionalAlphaNumericValidator(17, 17))
  )

  // representative details form fields
  val representativeName: TextInput = TextInput(
    name = "declaration.agent.name",
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val representativeAddressLine: TextInput = TextInput(
    name = "declaration.agent.address.line",
    labelKey = Some("common.fields.address.line"),
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val representativeAddressCityName: TextInput = TextInput(
    name = "declaration.agent.address.cityName",
    labelKey = Some("common.fields.address.cityName"),
    validators = Seq(OptionalAlphaNumericValidator(30))
  )
  val representativeAddressCountryCode: SelectInput = SelectInput(
    name = "declaration.agent.address.countryCode",
    options = countryTypes,
    labelKey = Some("common.fields.address.countryCode"),
    validators = Seq(OptionalContainsValidator(countryTypes.map(_._1).toSet))
  )
  val representativeAddressPostcode: TextInput = TextInput(
    name = "declaration.agent.address.postcodeId",
    labelKey = Some("common.fields.address.postcodeId"),
    validators = Seq(OptionalAlphaNumericValidator(9))
  )
  val representativeEori: TextInput = TextInput(
    name = "declaration.agent.id",
    labelKey = Some("common.fields.eori"),
    hintKey = Some("common.hints.eori"),
    validators = Seq(OptionalAlphaNumericValidator(17, 17))
  )
  val representativeFunctionCode: RadioInput = RadioInput(
    name = "declaration.agent.functionCode",
    options = agentFunctionCodes,
    inline = true,
    hintKey = Some("declaration.agent.functionCode.hint")
  )

  // importer details form fields
  val importerName: TextInput = TextInput(
    name = "declaration.goodsShipment.importer.name",
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val importerAddressLine: TextInput = TextInput(
    name = "declaration.goodsShipment.importer.address.line",
    labelKey = Some("common.fields.address.line"),
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val importerAddressCityName: TextInput = TextInput(
    name = "declaration.goodsShipment.importer.address.cityName",
    labelKey = Some("common.fields.address.cityName"),
    validators = Seq(OptionalAlphaNumericValidator(30))
  )
  val importerAddressCountryCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.importer.address.countryCode",
    options = countryTypes,
    labelKey = Some("common.fields.address.countryCode"),
    validators = Seq(OptionalContainsValidator(countryTypes.map(_._1).toSet))
  )
  val importerAddressPostcode: TextInput = TextInput(
    name = "declaration.goodsShipment.importer.address.postcodeId",
    labelKey = Some("common.fields.address.postcodeId"),
    validators = Seq(OptionalAlphaNumericValidator(9))
  )
  val importerEori: TextInput = TextInput(
    name = "declaration.goodsShipment.importer.id",
    labelKey = Some("common.fields.eori"),
    hintKey = Some("common.hints.eori"),
    validators = Seq(OptionalAlphaNumericValidator(17, 17))
  )

  // seller details form fields
  val sellerName: TextInput = TextInput(
    name = "declaration.goodsShipment.seller.name",
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val sellerAddressLine: TextInput = TextInput(
    name = "declaration.goodsShipment.seller.address.line",
    labelKey = Some("common.fields.address.line"),
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val sellerAddressCityName: TextInput = TextInput(
    name = "declaration.goodsShipment.seller.address.cityName",
    labelKey = Some("common.fields.address.cityName"),
    validators = Seq(OptionalAlphaNumericValidator(30))
  )
  val sellerAddressCountryCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.seller.address.countryCode",
    options = countryTypes,
    labelKey = Some("common.fields.address.countryCode"),
    validators = Seq(OptionalContainsValidator(countryTypes.map(_._1).toSet))
  )
  val sellerAddressPostcode: TextInput = TextInput(
    name = "declaration.goodsShipment.seller.address.postcodeId",
    labelKey = Some("common.fields.address.postcodeId"),
    validators = Seq(OptionalAlphaNumericValidator(9))
  )
  val sellerCommunicationsId: TextInput = TextInput(
    name = "declaration.goodsShipment.seller.communications[0].id",
    labelKey = Some("common.fields.communications.id"),
    validators = Seq(OptionalAlphaNumericValidator(50))
  )
  val sellerEori: TextInput = TextInput(
    name = "declaration.goodsShipment.seller.id",
    labelKey = Some("common.fields.eori"),
    hintKey = Some("common.hints.eori"),
    validators = Seq(OptionalAlphaNumericValidator(17, 17))
  )

  // buyer details form fields
  val buyerName: TextInput = TextInput(
    name = "declaration.goodsShipment.buyer.name",
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val buyerAddressLine: TextInput = TextInput(
    name = "declaration.goodsShipment.buyer.address.line",
    labelKey = Some("common.fields.address.line"),
    validators = Seq(OptionalAlphaNumericValidator(70))
  )
  val buyerAddressCityName: TextInput = TextInput(
    name = "declaration.goodsShipment.buyer.address.cityName",
    labelKey = Some("common.fields.address.cityName"),
    validators = Seq(OptionalAlphaNumericValidator(30))
  )
  val buyerAddressCountryCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.buyer.address.countryCode",
    options = countryTypes,
    labelKey = Some("common.fields.address.countryCode"),
    validators = Seq(OptionalContainsValidator(countryTypes.map(_._1).toSet))
  )
  val buyerAddressPostcode: TextInput = TextInput(
    name = "declaration.goodsShipment.buyer.address.postcodeId",
    labelKey = Some("common.fields.address.postcodeId"),
    validators = Seq(OptionalAlphaNumericValidator(9))
  )
  val buyerCommunicationsId: TextInput = TextInput(
    name = "declaration.goodsShipment.buyer.communications[0].id",
    labelKey = Some("common.fields.communications.id"),
    validators = Seq(OptionalAlphaNumericValidator(50))
  )
  val buyerEori: TextInput = TextInput(
    name = "declaration.goodsShipment.buyer.id",
    labelKey = Some("common.fields.eori"),
    hintKey = Some("common.hints.eori"),
    validators = Seq(OptionalAlphaNumericValidator(17, 17))
  )

  // additional supply chain actors form fields
  val mutualRecognitionPartyId: TextInput = TextInput(
    name = "declaration.goodsShipment.aeoMutualRecognitionParties[0].id",
    labelKey = Some("declaration.goodsShipment.aeoMutualRecognitionParties.id"),
    validators = Seq(OptionalAlphaNumericValidator(17, 17)) // we're expecting another EORI - do we want a field hint?
  )
  val mutualRecognitionPartyRoleCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.aeoMutualRecognitionParties[0].roleCode",
    options = partySubRoleTypes,
    labelKey = Some("declaration.goodsShipment.aeoMutualRecognitionParties.roleCode"),
    hintKey = Some("declaration.goodsShipment.aeoMutualRecognitionParties.roleCode.hint"),
    validators = Seq(OptionalContainsValidator(partySubRoleTypes.map(_._1).toSet))
  )
  val authorisationHolderId: TextInput = TextInput(
    name = "declaration.authorisationHolders[0].id",
    labelKey = Some("declaration.authorisationHolders.id"),
    validators = Seq(OptionalAlphaNumericValidator(17, 17)) // we're expecting another EORI - do we want a field hint?
  )
  val authorisationHolderCategoryCode: SelectInput = SelectInput(
    name = "declaration.authorisationHolders[0].categoryCode",
    options = partyRoleAuthorizationTypes,
    labelKey = Some("declaration.authorisationHolders.categoryCode"),
    validators = Seq(OptionalContainsValidator(partyRoleAuthorizationTypes.map(_._1).toSet))
  )

  // additional fiscal references form fields
  // TODO these are left as optional until the question re the "OSR" status is answered.  Possibly that by choosing a "free circulation"
  val domesticTaxPartyId: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].domesticDutyTaxParties[0].id",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.domesticDutyTaxParties.id"),
    validators = Seq(OptionalAlphaNumericValidator(17)) // probably another EORI? max 17 in the schema anyway
  )
  val domesticTaxPartyRoleCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].domesticDutyTaxParties[0].roleCode",
    options = partySubRoleTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.domesticDutyTaxParties.roleCode"),
    validators = Seq(OptionalContainsValidator(partySubRoleTypes.map(_._1).toSet))
  )

  // previous documents form fields
  val previousDocumentCategoryCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.previousDocuments[0].categoryCode",
    options = documentCategory,
    labelKey = Some("declaration.goodsShipment.previousDocuments.categoryCode"),
    validators = Seq(OptionalContainsValidator(documentCategory.map(_._1).toSet))
  )
  val previousDocumentTypeCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.previousDocuments[0].typeCode",
    options = documentType,
    labelKey = Some("declaration.goodsShipment.previousDocuments.typeCode"),
    validators = Seq(OptionalContainsValidator(documentType.map(_._1).toSet))
  )
  val previousDocumentId: TextInput = TextInput(
    name = "declaration.goodsShipment.previousDocuments[0].id",
    labelKey = Some("declaration.goodsShipment.previousDocuments.id"),
    validators = Seq(OptionalAlphaNumericValidator(35))
  )
  val previousDocumentLineNumeric: TextInput = TextInput(
    name = "declaration.goodsShipment.previousDocuments[0].lineNumeric",
    labelKey = Some("declaration.goodsShipment.previousDocuments.lineNumeric"),
    validators = Seq(OptionalNumericValidator(3, 0, 1, 999))
  )

  // procedure codes form fields
  val currentProcedureCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].governmentProcedures[0].currentCode",
    options = governmentProcedureTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.governmentProcedures.currentCode"),
    validators = Seq(RequiredContainsValidator(governmentProcedureTypes.map(_._1).toSet))
  )
  val previousProcedureCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].governmentProcedures[0].previousCode",
    options = importPreviousProcedures,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.governmentProcedures.previousCode"),
    validators = Seq(RequiredContainsValidator(importPreviousProcedures.map(_._1).toSet))
  )
  val additionalProcedureCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].governmentProcedures[0].additionalProcedure", // FIXME not currently in model - does it exist in schema??
    options = specialProcedureTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.governmentProcedures.additionalProcedure"),
    validators = Seq(RequiredContainsValidator(specialProcedureTypes.map(_._1).toSet))
  )

  // valuation form fields
  val valuationAdjustmentAdditionCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].valuationAdjustment.additionCode",
    options = valuationIndicatorTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.valuationAdjustment.additionCode"),
    validators = Seq(RequiredContainsValidator(valuationIndicatorTypes.map(_._1).toSet))
  )
  val commodityInvoiceLineAmount: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.invoiceLine.itemChargeAmount.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.invoiceLine.itemChargeAmount.value"),
    validators = Seq(OptionalNumericValidator(16, 2))
  )
  val commodityInvoiceLineCurrency: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.invoiceLine.itemChargeAmount.currencyId",
    options = currencyTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.invoiceLine.itemChargeAmount.currencyId"),
    validators = Seq(OptionalContainsValidator(currencyTypes.map(_._1).toSet))
  )
  // TODO blocked until clarified
//  val valuationExchangeRate: TextInput = ???
//  val valuationMethodType: SelectInput = ???
//  val valuationPreference: SelectInput = ???

  // tax form fields
  val commodityDutyTaxFeesTypeCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].typeCode",
    options = dutyTaxFeeTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.typeCode"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.typeCode.hint"),
    validators = Seq(OptionalContainsValidator(dutyTaxFeeTypes.map(_._1).toSet))
  )

  val commodityDutyTaxFeesQuantity: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].specificTaxBaseQuantity.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.specificTaxBaseQuantity.value"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.specificTaxBaseQuantity.value.hint"),
    validators = Seq(OptionalNumericValidator(16, 6))
  )

  val commodityDutyTaxFeesUnitCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].specificTaxBaseQuantity.unitCode", // originally, this duplicated property path for type code (above) - I assumed that was a mistake
    options = measureUnitTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.specificTaxBaseQuantity.unitCode"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.specificTaxBaseQuantity.unitCode.hint"),
    validators = Seq(OptionalContainsValidator(measureUnitTypes.map(_._1).toSet))
  )

  val commodityDutyTaxFeesPaymentAmount: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].payment.paymentAmount.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.payment.paymentAmount.value"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.payment.paymentAmount.value.hint"),
    validators = Seq(OptionalNumericValidator(16, 2))
  )

  val payabletaxamountCurrency: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].payment.paymentAmount.currencyId",
    options = currencyTypes,
    labelKey = Some("common.fields.currency"),
    validators = Seq(OptionalContainsValidator(currencyTypes.map(_._1).toSet))
  )

  val commodityDutyTaxFeesTaxAssessedAmount: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].payment.taxAssessedAmount.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.payment.taxAssessedAmount.value"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.payment.taxAssessedAmount.value.hint"),
    validators = Seq(OptionalNumericValidator(16, 2))
  )

  val calculationOfTaxesTotalCurrency: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].payment.taxAssessedAmount.currencyId",
    options = currencyTypes,
    labelKey = Some("common.fields.currency"),
    validators = Seq(OptionalContainsValidator(currencyTypes.map(_._1).toSet))
  )

  val commodityDutyTaxFeesPaymentMethodCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].payment.methodCode", // originally, this duplicated property path for type code (above) - I assumed that was a mistake
    options = paymentMethodTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.payment.methodCode"),
    validators = Seq(OptionalContainsValidator(paymentMethodTypes.map(_._1).toSet))
  )

  // identification of goods form fields
  val commodityDescription: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.description",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.description"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.description.hint"),
    validators = Seq(OptionalAlphaNumericValidator(512))
  )
  val packagingMarksNumbersId: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].packagings[0].marksNumbersId",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.packagings.marksNumbersId"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.packagings.marksNumbersId.hint"),
    validators = Seq(OptionalAlphaNumericValidator(512))
  )

  val cusCode: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].id",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.cusCode"),
    validators = Seq(OptionalAlphaNumericValidator(8))
  )

  val commodityCodeCombinedNomenclatureCode: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].id",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.combinedNomenclatureCode"),
    validators = Seq(OptionalAlphaNumericValidator(8))
  )

  val commodityCodeTaricCode: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].id",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.commodityCodeTaricCode"),
    validators = Seq(OptionalAlphaNumericValidator(2))
  )

  val commodityCodeTaricAdditionalCodes: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].id",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.commodityCodeTaricAdditionalCodes"),
    validators = Seq(OptionalAlphaNumericValidator(4))
  )

  val commodityCodeNationalAdditionalCodes: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].id",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.commodityCodeNationalAdditionalCodes"),
    validators = Seq(OptionalAlphaNumericValidator(4))
  )

  val commodityCodeCombinedNomenclatureTypeCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].identificationTypeCode",
    options = commodityClassificationType,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.commodityCodeCombinedNomenclatureTypeCode"),
    validators = Seq(OptionalContainsValidator(commodityClassificationType.map(_._1).toSet))
  )

  val commodityCodeTaricTypeCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].identificationTypeCode",
    options = commodityClassificationType,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.commodityCodeTaricTypeCode"),
    validators = Seq(OptionalContainsValidator(commodityClassificationType.map(_._1).toSet))
  )

  val commodityCodeTaricAdditionalTypeCodes: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].identificationTypeCode",
    options = commodityClassificationType,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.commodityCodeTaricAdditionalTypeCodes"),
    validators = Seq(OptionalContainsValidator(commodityClassificationType.map(_._1).toSet))
  )

  val commodityCodeNationalAdditionalTypeCodes: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.classifications[0].identificationTypeCode",
    options = commodityClassificationType,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.classifications.commodityCodeNationalAdditionalTypeCodes"),
    validators = Seq(OptionalContainsValidator(commodityClassificationType.map(_._1).toSet))
  )


  val supplementaryUnits: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.goodsMeasure.tariffQuantity.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.goodsMeasure.tariffQuantity"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.goodsMeasure.tariffQuantityHint"),
    validators = Seq(OptionalNumericValidator(16, 6))
  )

  val netMass: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.goodsMeasure.netWeightMeasure.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.goodsMeasure.netNetWeightMeasure"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.goodsMeasure.netNetWeightMeasureHint"),
    validators = Seq(RequiredNumericValidator(16, 6))
  )

  val grossMass: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.goodsMeasure.grossMassMeasure.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.goodsMeasure.grossMassMeasure"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.goodsMeasure.grossMassMeasureHint"),
    validators = Seq(RequiredNumericValidator(16, 6))
  )

  val typeOfPackages: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].packagings[0].typeCode",
    options = MoreOptions.packageTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.packagings.typeCode"),
    validators = Seq(RequiredContainsValidator(MoreOptions.packageTypes.map(_._1).toSet))
  )

  val numberOfPackages: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].packagings[0].quantity",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.packagings.quantity"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.packagings.quantityHint"),
    validators = Seq(RequiredNumericValidator(8))
  )

  val containerIdentificationNumber: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.transportEquipments[0].id",
    labelKey = Some("declaration.goodsShipment.consignment.transportEquipments.id"),
    hintKey = Some("declaration.goodsShipment.consignment.transportEquipments.idHint"),
    validators = Seq(OptionalNumericValidator(17))
  )

  // additions and deductions form fields
  val otherChargeDeductionAmount: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].customsValuation.chargeDeductions[0].otherChargeDeductionAmount.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.customsValuation.chargeDeductions.otherChargeDeductionAmount.value"),
    validators = Seq(OptionalNumericValidator(16, 2))
  )
  val otherChargeDeductionCurrency: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].customsValuation.chargeDeductions[0].otherChargeDeductionAmount.currencyId",
    options = currencyTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.customsValuation.chargeDeductions.otherChargeDeductionAmount.currencyId"),
    validators = Seq(OptionalContainsValidator(currencyTypes.map(_._1).toSet))
  )
  val otherChargeDeductionType: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].customsValuation.chargeDeductions[0].chargesTypeCode",
    options = GoodsItemValuationAdjustmentTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.customsValuation.chargeDeductions.chargesTypeCode"),
    validators = Seq(OptionalContainsValidator(GoodsItemValuationAdjustmentTypes.map(_._1).toSet))
  )

  // additional information form fields
  val additionalInformationStatementCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].additionalInformations[0].statementCode",
    options = specialMentionTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.additionalInformations.statementCode"),
    validators = Seq(OptionalContainsValidator(specialMentionTypes.map(_._1).toSet))
  )

  val additionalInformationStatementDescription: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].additionalInformations[0].statementDescription",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.additionalInformations.statementDescription"),
    validators = Seq(OptionalAlphaNumericValidator(512))
  )

  // country of origin form fields
  val originCountry: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].origins[0].countryCode",
    options = countryTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.origins.countryCode"),
    validators = Seq(RequiredContainsValidator(countryTypes.map(_._1).toSet))
  )

  val originType: RadioInput = RadioInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].origins[0].typeCode",
    options = countryRegionSubRoleTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.origins.typeCode")
  )

  // summary of goods form fields
  val declarationTotalPackageQuantity: TextInput = TextInput(
    name = "declaration.totalPackageQuantity",
    labelKey = Some("declaration.totalPackageQuantity"),
    validators = Seq(RequiredNumericValidator(8))
  )

  val goodsMeasureGrossMassMeasure: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.goodsMeasure.grossMassMeasure.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.goodsMeasure.grossMassMeasure.value"),
    validators = Seq(RequiredNumericValidator(16, 6))
  )

  // place of despatch form fields
  val exportCountry: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].exportCountry.id",
    options = thirdSpecialTerritoriesCategories,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.exportCountry.id"),
    validators = Seq(RequiredContainsValidator(thirdSpecialTerritoriesCategories.map(_._1).toSet))
  )
  val loadingLocation: SelectInput = SelectInput(
    name = "declaration.goodsShipment.consignment.loadingLocation.id",
    options = airportCodes,
    labelKey = Some("declaration.goodsShipment.consignment.loadingLocation.id"),
    validators = Seq(RequiredContainsValidator(airportCodes.map(_._1).toSet))
  )
  val destinationCountry: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].destination.countryCode",
    options = euCountries,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.destination.countryCode"),
    validators = Seq(RequiredContainsValidator(euCountries.map(_._1).toSet))
  )
// TODO - Set to "euCountries" assuming this is an H1 type, but for I1, needs to be "NonThirdCountries"

  // other data elements
  val dutyTaxFeeQuotaOrderID: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].commodity.dutyTaxFees[0].quotaOrderId",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.commodity.dutyTaxFees.quotaOrderId"),
    validators = Seq(OptionalAlphaNumericValidator(6))
  )

  val natureOfTransaction: SelectInput = SelectInput(
    name = "declaration.goodsShipment.transactionNatureCode",
    options = transactionNatureTypes,
    labelKey = Some("declaration.goodsShipment.transactionNatureCode"),
    validators = Seq(RequiredContainsValidator(transactionNatureTypes.map(_._1).toSet))
  )

  val statisticalValue: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].statisticalValueAmount.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.statisticalValueAmount"),
    hintKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.statisticalValueAmount.hint"),
    validators = Seq(OptionalNumericValidator(16, 2))
  )

  val statisticalValueCurrency: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].statisticalValueAmount.currencyId",
    options = currencyTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.statisticalValueAmount.currencyId"),
    validators = Seq(OptionalContainsValidator(currencyTypes.map(_._1).toSet))
  )

  val writingOffIssuingAuthority: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].additionalDocuments[0].submitter.name",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.additionalDocuments.submitter.name"),
    validators = Seq(OptionalAlphaNumericValidator(70))
  )

  val writingOffDateOfValidity: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].additionalDocuments[0].effectiveDateTime.dateTimeString.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.additionalDocuments.effectiveDateTime"),
    validators = Seq(OptionalAlphaNumericValidator(8))
  )

  val writingOffQuantity: TextInput = TextInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].additionalDocuments[0].writeOff.quantity.value",
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.additionalDocuments.writeOff.quantity"),
    validators = Seq(OptionalNumericValidator(16, 6))
  )

  val writingOffMeasurementUnitQualifier: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].additionalDocuments[0].writeOff.quantity.unitCode",
    options = measureUnitTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.additionalDocuments.writeOff.quantity.unitCode"),
    validators = Seq(OptionalContainsValidator(measureUnitTypes.map(_._1).toSet))
  )

  // transport form fields
  val containerCode: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.containerCode",
    labelKey = Some("declaration.goodsShipment.consignment.containerCode")
  )

  val borderTransportMeans: SelectInput = SelectInput(
    name = "declaration.borderTransportMeans.modeCode",
    options = transportModeTypes,
    labelKey = Some("declaration.borderTransportMeans.modeCode"),
    validators = Seq(RequiredContainsValidator(transportModeTypes.map(_._1).toSet))
  )

  val arrivalTransportMeans: SelectInput = SelectInput(
    name = "declaration.goodsShipment.consignment.arrivalTransportMeans.modeCode",
    options = transportModeTypes,
    labelKey = Some("declaration.goodsShipment.consignment.arrivalTransportMeans.modeCode")
  )

  val arrivalTransportMeansIdentificationTypeCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.consignment.arrivalTransportMeans.identificationTypeCode",
    options = transportMeansIdentificationTypes,
    labelKey = Some("declaration.goodsShipment.consignment.arrivalTransportMeans.identificationTypeCode")
  )

  val arrivalTransportMeansIdentificationId: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.arrivalTransportMeans.id",
    labelKey = Some("declaration.goodsShipment.consignment.arrivalTransportMeans.id")
  )

  val transportEquipmentId: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.transportEquipments[0].id",
    labelKey = Some("declaration.goodsShipment.consignment.transportEquipments.id")
  )

  val borderTransportMeansRegistrationNationalityCode: SelectInput = SelectInput(
    name = "declaration.borderTransportMeans.registrationNationalityCode",
    options = countryTypes,
    labelKey = Some("declaration.borderTransportMeans.registrationNationalityCode")
  )

  // Location of goods form fields
  val locationOfGoodsIdentificationOfLocation: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.goodsLocation.name",
    labelKey = Some("declaration.goodsShipment.consignment.goodsLocation.name"),
    validators = Seq(OptionalAlphaNumericValidator(35))
  )

  val locationOfGoodsAdditionalIdentifier: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.goodsLocation.id",
    labelKey = Some("declaration.goodsShipment.consignment.goodsLocation.id"),
    validators = Seq(OptionalNumericValidator(3))
  )

  val locationOfGoodsTypeOfLocation: SelectInput = SelectInput(
    name = "declaration.goodsShipment.consignment.goodsLocation.typeCode",
    options = goodsLocationTypeCode,
    labelKey = Some("declaration.goodsShipment.consignment.goodsLocation.typeCode"),
    validators = Seq(RequiredContainsValidator(goodsLocationTypeCode.map(_._1).toSet))
  )

  val locationOfGoodsStreetAndNumber: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.goodsLocation.address.line",
    labelKey = Some("declaration.goodsShipment.consignment.goodsLocation.address.line"),
    validators = Seq(OptionalAlphaNumericValidator(70))
  )

  val locationOfGoodsPostcode: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.goodsLocation.address.postcodeId",
    labelKey = Some("common.fields.address.postcodeId"),
    validators = Seq(OptionalAlphaNumericValidator(9))
  )

  val locationOfGoodsCity: TextInput = TextInput(
    name = "declaration.goodsShipment.consignment.goodsLocation.address.cityName",
    labelKey = Some("common.fields.address.cityName"),
    validators = Seq(OptionalAlphaNumericValidator(35))
  )

  val locationOfGoodsCountry: SelectInput = SelectInput(
    name = "declaration.goodsShipment.consignment.goodsLocation.address.countryCode",
    options = countryOptions,
    labelKey = Some("common.fields.address.countryCode"),
    validators = Seq(OptionalContainsValidator(countryOptions.map(_._1).toSet))
  )

  val locationOfGoodsQualifierOfTheIdentification: SelectInput = SelectInput(
    name = "declaration.goodsShipment.consignment.goodsLocation.address.typeCode",
    options = goodsLocationTypeCode,
    labelKey = Some("declaration.goodsShipment.consignment.goodsLocation.address.typeCode"),
    validators = Seq(RequiredContainsValidator(goodsLocationTypeCode.map(_._1).toSet))
  )

  // Warehouse and customs offices form fields
  val identificationOfWarehouseWarehouseType: SelectInput = SelectInput(
    name = "declaration.goodsShipment.warehouse.typeCode",
    options = customsWareHouseTypes,
    labelKey = Some("declaration.goodsShipment.warehouse.typeCode"),
    validators = Seq(OptionalContainsValidator(customsWareHouseTypes.map(_._1).toSet))
  )

  val identificationOfWarehouseWarehouseIdentifier: TextInput = TextInput(
    name = "declaration.goodsShipment.warehouse.id",
    labelKey = Some("declaration.goodsShipment.warehouse.id"),
    validators = Seq(OptionalAlphaNumericValidator(35))
  )

  val customsOfficeOfPresentation: SelectInput = SelectInput(
    name = "declaration.presentationOffice.id",
    options = supervisingCustomsOffices,
    labelKey = Some("declaration.presentationOffice.id"),
    validators = Seq(OptionalContainsValidator(supervisingCustomsOffices.map(_._1).toSet))
  )

  val superivisingCustomsOffice: SelectInput = SelectInput(
    name = "declaration.supervisingOffice.id",
    options = supervisingCustomsOffices,
    labelKey = Some("declaration.supervisingOffice.id"),
    validators = Seq(OptionalContainsValidator(supervisingCustomsOffices.map(_._1).toSet))
  )

  // Delivery term form fields
  val deliveryTermsIncotermCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.tradeTerms.conditionCode",
    options = incoTermCodes,
    labelKey = Some("declaration.goodsShipment.tradeTerms.conditionCode"),
    hintKey = Some("declaration.goodsShipment.tradeTerms.conditionCode.hint"),
    validators = Seq(OptionalContainsValidator(incoTermCodes.map(_._1).toSet))
  )

  val deliveryTermsUnlocodeCode: TextInput = TextInput(
    name = "declaration.goodsShipment.tradeTerms.locationId",
    labelKey = Some("declaration.goodsShipment.tradeTerms.locationId"),
    validators = Seq(OptionalAlphaNumericValidator(17))
  )

  val deliveryTermsCountryCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.tradeTerms.locationName",
    options = countryTypes,
    labelKey = Some("common.fields.address.countryCode"),
    validators = Seq(OptionalContainsValidator(countryTypes.map(_._1).toSet))
  )

  // deferred payment form fields
  val declarationAdditionalDocumentId: TextInput = TextInput(
    name = "declaration.additionalDocuments[0].id",
    labelKey = Some("declaration.additionalDocuments.id"),
    validators = Seq(OptionalNumericValidator(7, 0))
  )

  val declarationAdditionalDocumentCategoryCode: SelectInput = SelectInput(
    name = "declaration.additionalDocuments[0].categoryCode",
    options = paymentMethodTypes,
    labelKey = Some("declaration.additionalDocuments.categoryCode")
  )

  val declarationAdditionalDocumentTypeCode: TextInput = TextInput(
    name = "declaration.additionalDocuments[0].typeCode",
    labelKey = Some("declaration.additionalDocuments.typeCode"),
    validators = Seq(OptionalAlphaNumericValidator(3))
  )

  val invoiceAmountValue: TextInput = TextInput(
    name = "declaration.invoiceAmount.value",
    labelKey = Some("declaration.invoiceAmount.value"),
    validators = Seq(OptionalNumericValidator(16,2))
  )

  val invoiceAmountCurrency: SelectInput = SelectInput(
    name = "declaration.invoiceAmount.currencyId",
    options = currencyTypes,
    labelKey = Some("declaration.invoiceAmount.currencyId")
  )

  // guarantee type form fields
  val securityDetailsCode: SelectInput = SelectInput(
    name = "declaration.obligationGuarantees[0].securityDetailsCode",
    options = declaredGuaranteeTypes,
    labelKey = Some("declaration.obligationGuarantees.securityDetailsCode")
  )

  val guaranteeReferenceId: TextInput = TextInput(
    name = "declaration.obligationGuarantees[0].referenceId",
    labelKey = Some("declaration.obligationGuarantees.referenceId"),
    validators = Seq(OptionalAlphaNumericValidator(35))
  )

  val transactionNatureCode: SelectInput = SelectInput(
    name = "declaration.goodsShipment.governmentAgencyGoodsItems[0].transactionNatureCode",
    options = transactionNatureTypes,
    labelKey = Some("declaration.goodsShipment.governmentAgencyGoodsItems.transactionNatureCode"),
    validators = Seq(RequiredContainsValidator(transactionNatureTypes.map(_._1).toSet))
  )

}

object Fields extends Fields {

  private val typeMirror = runtimeMirror(this.getClass.getClassLoader)
  private val instanceMirror = typeMirror.reflect(this)
  private val members = instanceMirror.symbol.typeSignature.members

  val definitions: Map[String, FieldDefinition] = fields.map { f =>
    val t = fieldMirror(f).get.asInstanceOf[FieldDefinition]
    t.name -> t
  }.toMap

  private def fields: Iterable[universe.Symbol] = members.filter(_.typeSignature.baseClasses.contains(typeOf[FieldDefinition].typeSymbol))

  private def fieldMirror(symbol: Symbol): universe.FieldMirror = instanceMirror.reflectField(symbol.asTerm)
}

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

package domain

import domain.DeclarationFormats._
import uk.gov.hmrc.http.cache.client.CacheMap
import uk.gov.hmrc.wco.dec._
import services.cachekeys.TypedIdentifier
import services.cachekeys.TypedIdentifier._
import services.cachekeys.CacheKey._

object MetaDataConverter {

  def asMetaData(cache: CacheMap): TypedIdentifier[_] => MetaData = {
    case DeclarantDetailsId =>
      MetaData(declaration = Some(Declaration(declarant = cache.getEntry[ImportExportParty](declarantDetails.key))))

    case ReferencesId => {
      val refData = cache.getEntry[References](references.key)
      MetaData(declaration = Some(Declaration(
        typeCode = refData.flatMap(_.typeCode),
        functionalReferenceId = refData.flatMap(_.functionalReferenceId),
        goodsShipment = Some(GoodsShipment(
          transactionNatureCode = refData.flatMap(_.transactionNatureCode),
          ucr = Some(Ucr(traderAssignedReferenceId = refData.flatMap(_.traderAssignedReferenceId)))
        ))
      )))
    }

    case ExporterId =>
      MetaData(declaration = Some(Declaration(exporter = cache.getEntry[ImportExportParty](exporter.key))))

    case RepresentativeId =>
      MetaData(declaration = Some(Declaration(agent = cache.getEntry[Agent](representative.key))))

    case ImporterId =>
      MetaData(declaration = Some(Declaration(goodsShipment = Some(GoodsShipment(importer = cache.getEntry[ImportExportParty](importer.key))))))

    case _ => throw new MatchError("Hide exhaustivity warnings, remove before merging!!!!!!")
  }

}
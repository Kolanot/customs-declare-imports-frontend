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

package typeclasses

import Monoid.ops._
import org.scalacheck.Arbitrary._
import org.scalacheck.Gen
import org.scalacheck.Gen._
import org.scalatest.prop.PropertyChecks
import org.scalatest.{MustMatchers, WordSpec}

class OptionMonoidLaws          extends MonoidLaws(option(arbitrary[String]))
class RecursiveOptionMonoidLaws extends MonoidLaws(option(zip(arbitrary[String], arbitrary[Int])))
class Tuple2MonoidLaws          extends MonoidLaws(zip(option(arbitrary[String]), option(arbitrary[Int])))
class SeqMonoidLaws             extends MonoidLaws[Seq[String]](listOf(arbitrary[String]))

abstract class MonoidLaws[T: Monoid](gen: Gen[T]) extends WordSpec
  with MustMatchers
  with PropertyChecks {

  "right identity law" in {

    forAll(gen) { instance =>

      instance.append(Monoid.empty) mustBe instance
    }
  }

  "left identity law" in {

    forAll(gen) { instance =>

      Monoid.empty.append(instance) mustBe instance
    }
  }

  "associativity law" in {

    forAll(gen, gen, gen) { (m1, m2, m3) =>

      m1.append(m2).append(m3) mustBe m1.append(m2.append(m3))
    }
  }
}
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

import play.api.data.validation.ValidationError
import play.api.i18n.Messages

import scala.collection.mutable


object ListOptions {

  val borderTransportMeansModeCodes = mutable.LinkedHashMap("C"-> "C","F"->"F","Z"-> "Z","Y"->"Y")

  val DocumentCategory = mutable.LinkedHashMap("no list data currently"-> "no list data currently")

  val DocumentType = mutable.LinkedHashMap("235" -> "235",
    "270" -> "270",
    "271" -> "271",
    "325" -> "325",
    "337" -> "337",
    "355" -> "355",
    "380" -> "380",
    "703" -> "703",
    "704" -> "704",
    "705" -> "705",
    "714" -> "714",
    "720" -> "720",
    "730" -> "730",
    "740" -> "740",
    "741" -> "741",
    "750" -> "750",
    "760" -> "760",
    "785" -> "785",
    "787" -> "787",
    "820" -> "820",
    "821" -> "821",
    "822" -> "822",
    "823" -> "823",
    "825" -> "825",
    "952" -> "952",
    "955" -> "955",
    "CLE" -> "CLE",
    "CSE" -> "CSE",
    "DCR" -> "DCR",
    "DCS" -> "DCS",
    "IF3" -> "IF3",
    "MCR" -> "MCR",
    "MNS" -> "MNS",
    "MRN" -> "MRN",
    "SDE" -> "SDE",
    "T2F" -> "T2F",
    "T2G" -> "T2G",
    "T2M" -> "T2M",
    "ZZZ" -> "ZZZ")

  val PartySubRoleTypes = mutable.LinkedHashMap("CS"-> "CS",
    "FR1"-> "FR1",
    "FR2"-> "FR2",
    "FR3"-> "FR3",
    "FR4"-> "FR4",
    "FW"-> "FW",
    "MF"-> "MF",
    "WH"-> "WH")

  val PartyRoleAuthorizationTypes = mutable.LinkedHashMap("ACE"-> "ACE",
    "ACP"-> "ACP",
    "ACR"-> "ACR",
    "ACT"-> "ACT",
    "AEOC"-> "AEOC",
    "AEOF"-> "AEOF",
    "AEOS"-> "AEOS",
    "APEX"-> "APEX",
    "AWB"-> "AWB",
    "BOI"-> "BOI",
    "BTI"-> "BTI",
    "CCL"-> "CCL",
    "CGU"-> "CGU",
    "CSDR"-> "CSDR",
    "CSE"-> "CSE",
    "CVA"-> "CVA",
    "CW1"-> "CW1",
    "CW2"-> "CW2",
    "CWP"-> "CWP",
    "DEP"-> "DEP",
    "DPO"-> "DPO",
    "EIR"-> "EIR",
    "EORI"-> "EORI",
    "EPSS"-> "EPSS",
    "ETD"-> "ETD",
    "EUS"-> "EUS",
    "EXEE"-> "EXEE",
    "EXOR"-> "EXOR",
    "EXW"-> "EXW",
    "EXWH"-> "EXWH",
    "FAS"-> "FAS",
    "FZ"-> "FZ",
    "GGA"-> "GGA",
    "GVS"-> "GVS",
    "IPO"-> "IPO",
    "LVBI"-> "LVBI",
    "OPO"-> "OPO",
    "REM"-> "REM",
    "REP"-> "REP",
    "REX"-> "REX",
    "RSS"-> "RSS",
    "SAS"-> "SAS",
    "SASP"-> "SASP",
    "SDE"-> "SDE",
    "SIVA"-> "SIVA",
    "SSE"-> "SSE",
    "TEA"-> "TEA",
    "TEAH"-> "TEAH",
    "TRD"-> "TRD",
    "TST"-> "TST",
    "UKCS"-> "UKCS")

  val countryOptions = mutable.LinkedHashMap("AF"->"Afghanistan",
    "AX"->"Aland Islands",
    "AL"->"Albania",
    "DZ"->"Algeria",
    "AS"->"American Samoa",
    "AD"->"Andorra",
    "AO"->"Angola",
    "AI"->"Anguilla",
    "AQ"->"Antarctica",
    "AG"->"Antigua and Barbuda",
    "AR"->"Argentina",
    "AM"->"Armenia",
    "AW"->"Aruba",
    "AU"->"Australia",
    "AT"->"Austria",
    "AZ"->"Azerbaijan",
    "BS"->"Bahamas",
    "BH"->"Bahrain",
    "BD"->"Bangladesh",
    "BB"->"Barbados",
    "BY"->"Belarus",
    "BE"->"Belgium",
    "BZ"->"Belize",
    "BJ"->"Benin",
    "BM"->"Bermuda",
    "BT"->"Bhutan",
    "BO"->"Bolivia",
    "BA"->"Bosnia and Herzegovina",
    "BW"->"Botswana",
    "BV"->"Bouvet Island",
    "BR"->"Brazil",
    "IO"->"British Indian Ocean Territory"
    ,"BN"->"Brunei Darussalam"
    ,"BG"->"Bulgaria"
    ,"BF"->"Burkina Faso"
    ,"BI"->"Burundi"
    ,"KH"->"Cambodia"
    ,"CM"->"Cameroon"
    ,"CA"->"Canada"
    ,"CV"->"Cape Verde"
    ,"KY"->"Cayman Islands"
    ,"CF"->"Central African Republic"
    ,"TD"->"Chad"
    ,"CL"->"Chile"
    ,"CN"->"China"
    ,"HK"->"Hong Kong, SAR China"
    ,"MO"->"Macao, SAR China"
    ,"CX"->"Christmas Island"
    ,"CC"->"Cocos (Keeling) Islands"
    ,"CO"->"Colombia"
    ,"KM"->"Comoros"
    ,"CG"->"Congo (Brazzaville)"
    ,"CD"->"Congo, (Kinshasa)"
    ,"CK"->"Cook Islands"
    ,"CR"->"Costa Rica"
    ,"CI"->"Côte d'Ivoire"
    ,"HR"->"Croatia"
    ,"CU"->"Cuba"
    ,"CY"->"Cyprus"
    ,"CZ"->"Czech Republic"
    ,"DK"->"Denmark"
    ,"DJ"->"Djibouti"
    ,"DM"->"Dominica"
    ,"DO"->"Dominican Republic"
    ,"EC"->"Ecuador"
    ,"EG"->"Egypt"
    ,"SV"->"El Salvador"
    ,"GQ"->"Equatorial Guinea"
    ,"ER"->"Eritrea"
    ,"EE"->"Estonia"
    ,"ET"->"Ethiopia"
    ,"FK"->"Falkland Islands (Malvinas)"
    ,"FO"->"Faroe Islands"
    ,"FJ"->"Fiji"
    ,"FI"->"Finland"
    ,"FR"->"France"
    ,"GF"->"French Guiana"
    ,"PF"->"French Polynesia"
    ,"TF"->"French Southern Territories"
    ,"GA"->"Gabon"
    ,"GM"->"Gambia"
    ,"GE"->"Georgia"
    ,"DE"->"Germany"
    ,"GH"->"Ghana"
    ,"GI"->"Gibraltar"
    ,"GR"->"Greece"
    ,"GL"->"Greenland"
    ,"GD"->"Grenada"
    ,"GP"->"Guadeloupe"
    ,"GU"->"Guam"
    ,"GT"->"Guatemala"
    ,"GG"->"Guernsey"
    ,"GN"->"Guinea"
    ,"GW"->"Guinea-Bissau"
    ,"GY"->"Guyana"
    ,"HT"->"Haiti"
    ,"HM"->"Heard and Mcdonald Islands"
    ,"VA"->"Holy See (Vatican City State)"
    ,"HN"->"Honduras"
    ,"HU"->"Hungary"
    ,"IS"->"Iceland"
    ,"IN"->"India"
    ,"ID"->"Indonesia"
    ,"IR"->"Iran, Islamic Republic of"
    ,"IQ"->"Iraq"
    ,"IE"->"Ireland"
    ,"IM"->"Isle of Man"
    ,"IL"->"Israel"
    ,"IT"->"Italy"
    ,"JM"->"Jamaica"
    ,"JP"->"Japan"
    ,"JE"->"Jersey"
    ,"JO"->"Jordan"
    ,"KZ"->"Kazakhstan"
    ,"KE"->"Kenya"
    ,"KI"->"Kiribati"
    ,"KP"->"Korea (North)"
    ,"KR"->"Korea (South)"
    ,"KW"->"Kuwait"
    ,"KG"->"Kyrgyzstan"
    ,"LA"->"Lao PDR"
    ,"LV"->"Latvia"
    ,"LB"->"Lebanon"
    ,"LS"->"Lesotho"
    ,"LR"->"Liberia"
    ,"LY"->"Libya"
    ,"LI"->"Liechtenstein"
    ,"LT"->"Lithuania"
    ,"LU"->"Luxembourg"
    ,"MK"->"Macedonia, Republic of"
    ,"MG"->"Madagascar"
    ,"MW"->"Malawi"
    ,"MY"->"Malaysia"
    ,"MV"->"Maldives"
    ,"ML"->"Mali"
    ,"MT"->"Malta"
    ,"MH"->"Marshall Islands"
    ,"MQ"->"Martinique"
    ,"MR"->"Mauritania"
    ,"MU"->"Mauritius"
    ,"YT"->"Mayotte"
    ,"MX"->"Mexico"
    ,"FM"->"Micronesia, Federated States of"
    ,"MD"->"Moldova"
    ,"MC"->"Monaco"
    ,"MN"->"Mongolia"
    ,"ME"->"Montenegro"
    ,"MS"->"Montserrat"
    ,"MA"->"Morocco"
    ,"MZ"->"Mozambique"
    ,"MM"->"Myanmar"
    ,"NA"->"Namibia"
    ,"NR"->"Nauru"
    ,"NP"->"Nepal"
    ,"NL"->"Netherlands"
    ,"AN"->"Netherlands Antilles"
    ,"NC"->"New Caledonia"
    ,"NZ"->"New Zealand"
    ,"NI"->"Nicaragua"
    ,"NE"->"Niger"
    ,"NG"->"Nigeria"
    ,"NU"->"Niue"
    ,"NF"->"Norfolk Island"
    ,"MP"->"Northern Mariana Islands"
    ,"NO"->"Norway"
    ,"OM"->"Oman"
    ,"PK"->"Pakistan"
    ,"PW"->"Palau"
    ,"PS"->"Palestinian Territory"
    ,"PA"->"Panama"
    ,"PG"->"Papua New Guinea"
    ,"PY"->"Paraguay"
    ,"PE"->"Peru"
    ,"PH"->"Philippines"
    ,"PN"->"Pitcairn"
    ,"PL"->"Poland"
    ,"PT"->"Portugal"
    ,"PR"->"Puerto Rico"
    ,"QA"->"Qatar"
    ,"RE"->"Réunion"
    ,"RO"->"Romania"
    ,"RU"->"Russian Federation"
    ,"RW"->"Rwanda"
    ,"BL"->"Saint-Barthélemy"
    ,"SH"->"Saint Helena"
    ,"KN"->"Saint Kitts and Nevis"
    ,"LC"->"Saint Lucia"
    ,"MF"->"Saint-Martin (French part)"
    ,"PM"->"Saint Pierre and Miquelon"
    ,"VC"->"Saint Vincent and Grenadines"
    ,"WS"->"Samoa"
    ,"SM"->"San Marino"
    ,"ST"->"Sao Tome and Principe"
    ,"SA"->"Saudi Arabia"
    ,"SN"->"Senegal"
    ,"RS"->"Serbia"
    ,"SC"->"Seychelles"
    ,"SL"->"Sierra Leone"
    ,"SG"->"Singapore"
    ,"SK"->"Slovakia"
    ,"SI"->"Slovenia"
    ,"SB"->"Solomon Islands"
    ,"SO"->"Somalia"
    ,"ZA"->"South Africa"
    ,"GS"->"South Georgia and the South Sandwich Islands"
    ,"SS"->"South Sudan"
    ,"ES"->"Spain"
    ,"LK"->"Sri Lanka"
    ,"SD"->"Sudan"
    ,"SR"->"Suriname"
    ,"SJ"->"Svalbard and Jan Mayen Islands"
    ,"SZ"->"Swaziland"
    ,"SE"->"Sweden"
    ,"CH"->"Switzerland"
    ,"SY"->"Syrian Arab Republic (Syria)"
    ,"TW"->"Taiwan, Republic of China"
    ,"TJ"->"Tajikistan"
    ,"TZ"->"Tanzania, United Republic of"
    ,"TH"->"Thailand"
    ,"TL"->"Timor-Leste"
    ,"TG"->"Togo"
    ,"TK"->"Tokelau"
    ,"TO"->"Tonga"
    ,"TT"->"Trinidad and Tobago"
    ,"TN"->"Tunisia"
    ,"TR"->"Turkey"
    ,"TM"->"Turkmenistan"
    ,"TC"->"Turks and Caicos Islands"
    ,"TV"->"Tuvalu"
    ,"UG"->"Uganda"
    ,"UA"->"Ukraine"
    ,"AE"->"United Arab Emirates"
    ,"GB"->"United Kingdom"
    ,"US"->"United States of America"
    ,"UM"->"US Minor Outlying Islands"
    ,"UY"->"Uruguay"
    ,"UZ"->"Uzbekistan"
    ,"VU"->"Vanuatu"
    ,"VE"->"Venezuela (Bolivarian Republic)"
    ,"VN"->"Viet Nam"
    ,"VI"->"Virgin Islands, US"
    ,"WF"->"Wallis and Futuna Islands"
    ,"EH"->"Western Sahara"
    ,"YE"->"Yemen"
    ,"ZM"->"Zambia"
    ,"ZW"->"Zimbabwe")

  val GovernmentProcedureTypes = mutable.LinkedHashMap(
    "10" -> "10",
    "11" -> "11",
    "21" -> "21",
    "22" -> "22",
    "31" -> "31",
    "40" -> "40",
    "45" -> "45",
    "48" -> "48",
    "51" -> "51",
    "53" -> "53",
    "54" -> "54",
    "61" -> "61",
    "63" -> "63",
    "68" -> "68",
    "71" -> "71",
    "77" -> "77",
    "78" -> "78",
    "00" -> "00",
    "01" -> "01",
    "07" -> "07",
    "23" -> "23",
    "42" -> "42",
    "43" -> "43",
    "44" -> "44",
    "46" -> "46",
    "76" -> "76",
    "95" -> "95",
    "96" -> "96")

  val ImportPreviousProcedures = mutable.LinkedHashMap(
    "23" -> "23",
    "71" -> "71",
    "00" -> "00")

  val SpecialProcedureTypes = mutable.LinkedHashMap(
    "000" -> "000",
    "1IL" -> "1IL",
    "A04" -> "A04",
    "B02" -> "B02",
    "B03" -> "B03",
    "B06" -> "B06",
    "B51" -> "B51",
    "B52" -> "B52",
    "B53" -> "B53",
    "B54" -> "B54",
    "C01" -> "C01",
    "C02" -> "C02",
    "C03" -> "C03",
    "C04" -> "C04",
    "C06" -> "C06",
    "C07" -> "C07",
    "C08" -> "C08",
    "C09" -> "C09",
    "C10" -> "C10",
    "C11" -> "C11",
    "C12" -> "C12",
    "C13" -> "C13",
    "C14" -> "C14",
    "C15" -> "C15",
    "C16" -> "C16",
    "C17" -> "C17",
    "C18" -> "C18",
    "C19" -> "C19",
    "C20" -> "C20",
    "C21" -> "C21",
    "C22" -> "C22",
    "C23" -> "C23",
    "C24" -> "C24",
    "C25" -> "C25",
    "C26" -> "C26",
    "C27" -> "C27",
    "C28" -> "C28",
    "C29" -> "C29",
    "C30" -> "C30",
    "C31" -> "C31",
    "C32" -> "C32",
    "C33" -> "C33",
    "C34" -> "C34",
    "C35" -> "C35",
    "C36" -> "C36",
    "C37" -> "C37",
    "C38" -> "C38",
    "C39" -> "C39",
    "C40" -> "C40",
    "C41" -> "C41",
    "C42" -> "C42",
    "C43" -> "C43",
    "C44" -> "C44",
    "C45" -> "C45",
    "C46" -> "C46",
    "C47" -> "C47",
    "C48" -> "C48",
    "C49" -> "C49",
    "C50" -> "C50",
    "C51" -> "C51",
    "C52" -> "C52",
    "C53" -> "C53",
    "C54" -> "C54",
    "C55" -> "C55",
    "C56" -> "C56",
    "C57" -> "C57",
    "C58" -> "C58",
    "C59" -> "C59",
    "C60" -> "C60",
    "C61" -> "C61",
    "C71" -> "C71",
    "C72" -> "C72",
    "C73" -> "C73",
    "C74" -> "C74",
    "C75" -> "C75",
    "D01" -> "D01",
    "D02" -> "D02",
    "D03" -> "D03",
    "D04" -> "D04",
    "D05" -> "D05",
    "D06" -> "D06",
    "D07" -> "D07",
    "D08" -> "D08",
    "D09" -> "D09",
    "D10" -> "D10",
    "D11" -> "D11",
    "D12" -> "D12",
    "D13" -> "D13",
    "D14" -> "D14",
    "D15" -> "D15",
    "D16" -> "D16",
    "D17" -> "D17",
    "D18" -> "D18",
    "D19" -> "D19",
    "D20" -> "D20",
    "D21" -> "D21",
    "D22" -> "D22",
    "D23" -> "D23",
    "D24" -> "D24",
    "D25" -> "D25",
    "D26" -> "D26",
    "D27" -> "D27",
    "D28" -> "D28",
    "D29" -> "D29",
    "D30" -> "D30",
    "D51" -> "D51",
    "E01" -> "E01",
    "E02" -> "E02",
    "E51" -> "E51",
    "E52" -> "E52",
    "E53" -> "E53",
    "E61" -> "E61",
    "E62" -> "E62",
    "E63" -> "E63",
    "E64" -> "E64",
    "E65" -> "E65",
    "E71" -> "E71",
    "F01" -> "F01",
    "F02" -> "F02",
    "F03" -> "F03",
    "F04" -> "F04",
    "F05" -> "F05",
    "F06" -> "F06",
    "F07" -> "F07",
    "F15" -> "F15",
    "F21" -> "F21",
    "F22" -> "F22",
    "F31" -> "F31",
    "F32" -> "F32",
    "F33" -> "F33",
    "F34" -> "F34",
    "F44" -> "F44",
    "F45" -> "F45",
    "F46" -> "F46",
    "F47" -> "F47",
    "F61" -> "F61",
    "F65" -> "F65",
    "F75" -> "F75")
}


//package dispatch.json

package object testjson {
val somecode = """
{
  "events":[{
    "modifiedBy": "user@example.com",
    "timestamp": 1255935016481,
    "type": "BLIP_SUBMITTED",
    "properties": {
      "blipId": "b+ja8F_Hw4J"
    }
  }],
  "wavelet": {
    "creationTime": 1255934856713,
    "creator": "user@example.com",
    "lastModifiedTime": 1255935016481,
    "participants": [ "user@example.com","user2@example.com" ],
    "rootBlipId": "b+ja8F_Hw4J",
    "title": "",
    "version": 11,
    "waveId": "example.com!w+ja8F_Hw4I",
    "waveletId": "example.com!conv+root",
    "dataDocuments": {
    }
  },
  "blips": {
    "b+ja8F_Hw4J": {
      "annotations": [{
        "range": {
          "start": 0,
          "end": 1
        },
        "name": "conv/title",
        "value": ""
      }],
      "elements": {},
      "blipId": "b+ja8F_Hw4J",
      "childBlipIds": [],
      "contributors": ["user@example.com"],
      "creator": "user@example.com",
      "content": "\n",
      "lastModifiedTime": 1255934856708,
      "version": 6,
      "waveId": "google.com!w+ja8F_Hw4I",
      "waveletId": "example.com!conv+root"
    }
  },
  "robotAddress": "myrobot@example.com"
} 
"""
}


/*

HELLO: JsObject(Map(JsString(events) -> JsArray(List(JsObject(Map(JsString(modif
iedBy) -> JsString(user@example.com), JsString(timestamp) -> JsNumber(1255935016
481), JsString(type) -> JsString(BLIP_SUBMITTED), JsString(properties) -> JsObje
ct(Map(JsString(blipId) -> JsString(b+ja8F_Hw4J))))))), JsString(wavelet) -> JsO
bject(Map(JsString(creator) -> JsString(user@example.com), JsString(title) -> Js
String(), JsString(dataDocuments) -> JsObject(Map()), JsString(participants) ->
JsArray(List(JsString(user@example.com), JsString(user2@example.com))), JsString
(creationTime) -> JsNumber(1255934856713), JsString(waveId) -> JsString(example.
com!w+ja8F_Hw4I), JsString(waveletId) -> JsString(example.com!conv+root), JsStri
ng(lastModifiedTime) -> JsNumber(1255935016481), JsString(version) -> JsNumber(1
1), JsString(rootBlipId) -> JsString(b+ja8F_Hw4J))), JsString(blips) -> JsObject
(Map(JsString(b+ja8F_Hw4J) -> JsObject(Map(JsString(creator) -> JsString(user@ex
ample.com), JsString(contributors) -> JsArray(List(JsString(user@example.com))),
 JsString(childBlipIds) -> JsArray(List()), JsString(blipId) -> JsString(b+ja8F_
Hw4J), JsString(content) -> JsString(
), JsString(elements) -> JsObject(Map()), JsString(waveId) -> JsString(google.co
m!w+ja8F_Hw4I), JsString(waveletId) -> JsString(example.com!conv+root), JsString
(annotations) -> JsArray(List(JsObject(Map(JsString(range) -> JsObject(Map(JsStr
ing(start) -> JsNumber(0), JsString(end) -> JsNumber(1))), JsString(name) -> JsS
tring(conv/title), JsString(value) -> JsString())))), JsString(lastModifiedTime)
 -> JsNumber(1255934856708), JsString(version) -> JsNumber(6))))), JsString(robo
tAddress) -> JsString(myrobot@example.com)))
 
[type]

    events  -->

[type]

          modifiedBy  --> user@example.com
          timestamp  --> JsNumber(1255935016481)

          type  --> BLIP_SUBMITTED
          properties  -->
[type]

              blipId  --> b+ja8F_Hw4J
    wavelet  -->
[type]

        creator  --> user@example.com
        title  -->
        dataDocuments  -->
[type]

        participants  -->
user@example.comuser2@example.com
        creationTime  --> JsNumber(1255934856713)

        waveId  --> example.com!w+ja8F_Hw4I
        waveletId  --> example.com!conv+root
        lastModifiedTime  --> JsNumber(1255935016481)

        version  --> JsNumber(11)

        rootBlipId  --> b+ja8F_Hw4J
    blips  -->
[type]

        b+ja8F_Hw4J  -->
[type]

            creator  --> user@example.com
            contributors  -->
user@example.com
            childBlipIds  -->

            blipId  --> b+ja8F_Hw4J
            content  -->

            elements  -->
[type]

            waveId  --> google.com!w+ja8F_Hw4I
            waveletId  --> example.com!conv+root
            annotations  -->

[type]

                  range  -->
[type]

                      start  --> JsNumber(0)

                      end  --> JsNumber(1)

                  name  --> conv/title
                  value  -->
            lastModifiedTime  --> JsNumber(1255934856708)

            version  --> JsNumber(6)

    robotAddress  --> myrobot@example.com

 */



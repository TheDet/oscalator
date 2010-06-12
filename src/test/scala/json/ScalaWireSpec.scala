/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package oscalator.test.json

//import org.junit._
//import Assert._

import org.scalatest.Spec
import org.scalatest.matchers._
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
import de.detthedev.oscalator.wave.wire._
import de.detthedev.oscalator.wave.model._
import dispatch.json._


//@RunWith(classOf[JUnitRunner])
class ScalaWireSpec extends Spec with ShouldMatchers {
  describe("UnwireEvent") {
     it("should instanciate an Event object") {
       val json = """
       {
          "modifiedBy": "user@example.com",
          "timestamp": 1255935016481,
          "type": "BLIP_SUBMITTED",
          "properties": {
            "blipId": "b+ja8F_Hw4J"
          }
        }
       """
        val parsed = JsValue.fromString(json)
        val event = UnwireEvent(parsed)
        event.modifiedBy should equal ("user@example.com")
        event.timestamp should equal (1255935016481L)
        event.etype should equal ("BLIP_SUBMITTED") // Shall later be subclassed!
        event.properties should equal ( Map[String, Any]())  // Shall later be correct map
     }
  }

  describe("UnwireWavelet") {
     it("should instanciate a Wavelet object") {
       val json = """
       {
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
       }"""
        val parsed = JsValue.fromString(json)
        val wavelet = UnwireWavelet(parsed)

        wavelet.creator should equal ("user@example.com")
        wavelet.title should equal ("")
        wavelet.rootBlipId should equal ("b+ja8F_Hw4J")
        wavelet.waveId should equal ("example.com!w+ja8F_Hw4I")
        wavelet.waveletId should equal ("example.com!conv+root")

        wavelet.version should equal (11L)

        wavelet.creationTime should equal (1255934856713L)
        wavelet.lastModifiedTime should equal (1255935016481L)
        
        wavelet.participants should equal ( List[String]("user@example.com", "user2@example.com"))  // Shall later be correct List
        wavelet.dataDocuments should equal ( List[Any](""))  // Shall later be correct List
    }
  }

  describe("UnwireBlip") {
     it("should instanciate a Blip object") {
       val json = """
       {
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
       }"""
        val parsed = JsValue.fromString(json)
        val blip = UnwireBlip(parsed)

        blip.creator should equal ("user@example.com")
        blip.content should equal ("\n")
        blip.blipId should equal ("b+ja8F_Hw4J")
        blip.waveId should equal ("google.com!w+ja8F_Hw4I")
        blip.waveletId should equal ("example.com!conv+root")

        blip.lastModifiedTime should equal (1255934856708L)
        blip.version should equal (6L)

        blip.contributors should equal ( List[String]("user@example.com"))
        blip.childBlipIds should equal ( List[String]())

        //blip.elements should equal (Map[String, Any]())  // what type is Any??

        blip.annotations should equal (List[Annotation](Annotation("conv/title", "", Range(0,1))))
    }
  }

  describe("UnwireAnnotation") {
     it("should instanciate an Annotation object") {
       val json = """
       {
            "range": {
              "start": 0,
              "end": 1
            },
            "name": "conv/title",
            "value": ""
       }"""
        val parsed = JsValue.fromString(json)
        val annotation = UnwireAnnotation(parsed)

        annotation.name  should equal ("conv/title")
        annotation.value should equal ("")
        annotation.range should equal (Range(0,1))
     }
  }

  describe("UnwireRange") {
     it("should instanciate a Range object") {
       val json = """
       {
         "start": 0,
         "end": 1
       }"""
        val parsed = JsValue.fromString(json)
        val range = UnwireRange(parsed)

        range.start  should equal (0L)
        range.end should equal (1L)
     }
  }


  describe("UnwireBundle") {
     it("should instanciate a Bundle object") {
       val json = """
        {
          "events":[],
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
          "blips": {},
          "robotAddress": "myrobot@example.com"
        }
        """
        val parsed = JsValue.fromString(json)
        val bundle = UnwireBundle(parsed)       
        bundle.robotAddress should equal ("myrobot@example.com")
        bundle.events        should equal (List[Event]())
//        bundle.wavelet       should equal (Wavelet())
        bundle.blips         should equal (Map[String, Blip]())
     }
  }

    describe("Unwire main function") {
      it("should desconstruct a JSON string into a bundle object") {
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

         val result = Unwire(somecode)
      }
    }
}

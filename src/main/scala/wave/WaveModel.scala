package de.detthedev.oscalator.wave.model

case class Bundle(
    robotAddress:String,
    events:List[Event],
    wavelet:Wavelet,
    blips:Map[String, Blip]
)

case class Wavelet(
    creationTime: Long,
    creator: String,
    lastModifiedTime: Long,
    participants: List[String],
    rootBlipId: String,
    title: String,
    version: Long,
    waveId: String,
    waveletId: String,
    dataDocuments: List[Any]   // OPEN ... TODO
)

case class Event(
    modifiedBy:String,
    timestamp:Long,
    etype:String,     // NO:  BLIP ENVENT TYPE:   e.g.  "BLIP_SUBMITTED", Plus: subtyping Event
    properties:Map[String,Any]
     // e.g. "blipId": "b+ja8F_Hw4J"   // Either string-value map, or Properties object?
     // TODO: Google's Event is an interface and also contains getters for
     // blip, wavelet and bundle.
)

case class Annotation (
   name:String,
   value:String,
   range:Range
)
{
   // TODO: Google's Annotation contains a shift method, as
   // well as toString and some name (key) constants
     /**
   * Shifts this annotation by {@code shiftAmount} if it is on a range that
   * is after or covers the given position.
   *
   * @param position the anchor position.
   * @param shiftAmount the amount to shift the annotation range.
   */
//  def shift(position:Int, shiftAmount:Int) {
//    val start = range.start
//    if (start >= position) {
//      start += shiftAmount
//    }
//
//    val end = range.end
//    if (end >= position) {
//      end += shiftAmount
//    }
//    range = Range(start, end)
//  }
}



case class Range (
   start:Long,
   end:Long
   // TODO: Google's Range contains also hashCode, equals and toString method
)

case class Blip(
   blipId:String,
   waveId:String,
   waveletId:String,
   creator:String,
   contributors:List[String],
   content:String,
   childBlipIds:List[String],
   //JsString(elements) -> JsObject(Map()),  TODO !!
   annotations:List[Annotation],
   lastModifiedTime:Long,
   version:Long
)
/*
    creator:String  //creator  --> user@example.com
    contributors:List[String]    // contributors  -->
                                 //      user@example.com
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
*/
// STRUCTURE OF BLIP(S) 
/*
JsString(blips) -> 
  JsObject(Map(  
       JsString(b+ja8F_Hw4J) -> 
         JsObject(Map(
           JsString(creator) -> JsString(user@example.com), 
           JsString(contributors) -> JsArray(List(JsString(user@example.com))),
           JsString(childBlipIds) -> JsArray(List()), 
           JsString(blipId) -> JsString(b+ja8F_Hw4J), 
           JsString(content) -> JsString(), 
           JsString(elements) -> JsObject(Map()), 
           JsString(waveId) -> JsString(google.com!w+ja8F_Hw4I), 
           JsString(waveletId) -> JsString(example.com!conv+root), 
           JsString(annotations) -> JsArray(List(
              JsObject(Map(
                JsString(range) -> JsObject(Map(
                                     JsString(start) -> JsNumber(0), 
                                     JsString(end) -> JsNumber(1))), 
                JsString(name) -> JsString(conv/title), 
                JsString(value) -> JsString())))), 
           JsString(lastModifiedTime) -> JsNumber(1255934856708), 
           JsString(version) -> JsNumber(6)))))
*/


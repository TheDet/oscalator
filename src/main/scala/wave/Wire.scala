/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.detthedev.oscalator.wave.wire

import dispatch.json._
import de.detthedev.oscalator.wave.model._

// TODO:  Unwire objects should be classes, as they need the member variable m !! (or does implicit help??
  // Then we would need companion objects to keep the apply method !! think about this restruct!!
abstract class Unwire[T](x:JsValue) {
  var m:Map[JsString, JsValue] = adapt(x)

  def adapt(x:JsValue):Map[JsString,JsValue] = x match {
      case v:JsObject => toMap(v)
      case _ => throw new RuntimeException("Unwire: param is no JsObject")
  }

  def unwire:T

  implicit def string2JsString(x:String) = JsString(x)

  implicit def JsString2String(x:JsString) = x.self
  implicit def JsNumber2double(x:JsNumber) = x.self.doubleValue

  def toMap(x:JsObject):Map[JsString, JsValue] = x.self
//  def map[T](key:String)(f:JsValue=>T) = m(key).asInstanceOf[JsObject].self.map(e => (e._1.self, f(e._2)))
//  def map[T](key:String) = m(key).asInstanceOf[JsObject].self.map(e => (e._1.self, f(e._2)))
  def map[T](key:String) = m(key).asInstanceOf[JsObject].self
  def array(key:String):List[JsValue] = m(key).asInstanceOf[JsArray].self
  def jsobject(key:String):JsObject        = m(key).asInstanceOf[JsObject]
  def string(key:String):String = m(key) match { case x:JsString => x.self
                                                 case _          => println("ERROR"); "Error" }
  def number(key:String):Double = m(key) match { case x:JsNumber => x.self.doubleValue
                                                 case _          => println("ERROR"); 42 }
  def long(key:String):Long = number(key).toLong
  def date(key:String):java.util.Date = new java.util.Date(long(key))

  def stringarray(key:String) = array(key) map (x => x.asInstanceOf[JsString].self)
}

//case class Error(message:String)

trait UnwireObject[T] {
  def apply(x:JsValue):T = x match {
      case v:JsObject => unwire(v)
      case _ => throw new RuntimeException("Unwire Bundle: param is no JsObject")
  }
  def unwire(v:JsObject):T
}


object UnwireBundle extends UnwireObject[Bundle]{
  override def unwire(v:JsObject) =  new UnwireBundle(v).unwire
}
class UnwireBundle(x:JsObject) extends Unwire[Bundle](x) {
  def events(key:String)  = array(key) map (x => UnwireEvent(x))
  def wavelet(key:String) = UnwireWavelet( jsobject(key) )
  //def blips(key:String)   = array(key) map (x => UnwireBlip(x))
  def blips(key:String)   = map(key) map (e => (e._1.self, UnwireBlip(e._2)))

  def unwire = Bundle(
      robotAddress = string ("robotAddress"),
      events       = events ("events"),
      wavelet      = wavelet("wavelet"),
      blips        = blips  ("blips")
  )
}

object UnwireEvent extends UnwireObject[Event]{
  override def unwire(v:JsObject) =  new UnwireEvent(v).unwire
}
class UnwireEvent(x:JsObject) extends Unwire[Event](x) {
  def unwire = Event(   // ATTENTION: HERE A SWITCH SHOULD DECIDE FOR DIFFERENT SUBCLASSES!!
      modifiedBy = string("modifiedBy"),
      timestamp  = long("timestamp"),
      etype      = string("type"),
    // type:String,     // NO:  BLIP ENVENT TYPE:   e.g.  "BLIP_SUBMITTED",
//    properties: {
//      "blipId": "b+ja8F_Hw4J"
//    }
    //properties = m("properties").self   // presumingly to be deconstructed too
      properties = Map[String, Any]()  // TODO
  )
}

object UnwireWavelet extends UnwireObject[Wavelet]{
  override def unwire(v:JsObject) =  new UnwireWavelet(v).unwire
}
class UnwireWavelet(x:JsObject) extends Unwire[Wavelet](x) {
   def unwire = Wavelet(
       creationTime      = long  ("creationTime"),
       creator           = string("creator"),
       lastModifiedTime  = long  ("lastModifiedTime"),
       participants      = stringarray ("participants"),  
       rootBlipId        = string("rootBlipId"),
       title             = string("title"),
       version           = long  ("version"),
       waveId            = string("waveId"),
       waveletId         = string("waveletId"),
       dataDocuments     = List[Any]("")     // TODO
   )
}


object UnwireAnnotation extends UnwireObject[Annotation]{
  override def unwire(v:JsObject) =  new UnwireAnnotation(v).unwire
}
class UnwireAnnotation(x:JsObject) extends Unwire[Annotation](x) {
   def range(key:String) = UnwireRange( jsobject(key) )
   def unwire = Annotation(
        name  = string("name"),
        value = string("value"),
        range = range("range")
   )
}



object UnwireRange extends UnwireObject[Range]{
  override def unwire(v:JsObject) =  new UnwireRange(v).unwire
}
class UnwireRange(x:JsObject) extends Unwire[Range](x) {
   def unwire = Range(
        start = long("start"),
        end   = long("end")
   )
}

object UnwireBlip extends UnwireObject[Blip]{
  override def unwire(v:JsObject) =  new UnwireBlip(v).unwire
}
class UnwireBlip(x:JsObject) extends Unwire[Blip](x) {
   def annotations(key:String) = array(key) map (x => UnwireAnnotation(x) )
   def unwire = Blip(
       blipId = string("blipId"),
       waveId = string("waveId"),
       waveletId = string("waveletId"),
       creator = string("creator"),
       contributors = stringarray("contributors"),
       content = string("content"),
       childBlipIds = stringarray("childBlipIds"),
       //elements = JsObject(Map()),  TODO !!
       annotations = annotations("annotations"),
       lastModifiedTime = long("lastModifiedTime"),
       version = long("version")
   )
}

object Unwire {
   def apply(json:String) = {
     val parsed = JsValue.fromString(json)
     val bundle = UnwireBundle(parsed)
   }
}
//class UnwireXXX(x:JsObject) extends Unwire[T] {
//}

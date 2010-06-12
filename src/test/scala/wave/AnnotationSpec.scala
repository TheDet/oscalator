package oscalator.test.wave

/**
 *
 * @author Det
 */

//import org.junit._
//import Assert._

import org.scalatest.Spec
import org.scalatest.matchers._
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
import de.detthedev.oscalator.wave.wire._
import de.detthedev.oscalator.wave.model._


//@RunWith(classOf[JUnitRunner])
class AnnotationSpec extends Spec with ShouldMatchers {
  describe("Annotation") {
     it("should shift an Annotation object") {
        // This test has been taken from Google's AnnotationTest
        var annotation = Annotation("key", "value", Range(5 , 10))

//        annotation.shift(4, 2)
//        assertEquals(7, annotation.getRange().getStart())
//        assertEquals(12, annotation.getRange().getEnd())
//
//        annotation = Annotation("key", "value", Range(5 , 10)
//        annotation.shift(5, 3)
//        assertEquals(8, annotation.getRange().getStart())
//        assertEquals(13, annotation.getRange().getEnd())
//
//        annotation = Annotation("key", "value", Range(5 , 10))
//        annotation.shift(9, 2)
//        assertEquals(5, annotation.getRange().getStart())
//        assertEquals(12, annotation.getRange().getEnd())
//
//        annotation = Annotation("key", "value", Range(5 , 10))
//        annotation.shift(10, 2)
//        assertEquals(5, annotation.getRange().getStart())
//        assertEquals(12, annotation.getRange().getEnd())
     }
  }
}
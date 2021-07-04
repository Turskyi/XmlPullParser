package io.github.turskyi.xmlpullparser

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.StringReader

class MainActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "myLogs"
    }

    /** Called when the activity is first created.  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDataFromStringXml()
        showDataFromXml()
    }

    private fun showDataFromStringXml() {
        try {
            var tmp: String
            val xpp: XmlPullParser =
                "<data><phone><company>Samsung</company></phone></data>".prepareXpp()
            while (xpp.eventType != XmlPullParser.END_DOCUMENT) {
                when (xpp.eventType) {
                    XmlPullParser.START_DOCUMENT -> Log.d(LOG_TAG, "START_DOCUMENT")
                    XmlPullParser.START_TAG -> {
                        Log.d(LOG_TAG, "START_TAG: name = " + xpp.name
                                + ", depth = " + xpp.depth + ", attrCount = "
                                + xpp.attributeCount)
                        tmp = ""
                        var i = 0
                        while (i < xpp.attributeCount) {
                            tmp = (tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ")
                            i++
                        }
                        if (!TextUtils.isEmpty(tmp)) Log.d(LOG_TAG, "Attributes: $tmp")
                    }
                    XmlPullParser.END_TAG -> Log.d(LOG_TAG, "END_TAG: name = " + xpp.name)
                    XmlPullParser.TEXT -> Log.d(LOG_TAG, "text = " + xpp.text)
                    else -> {
                    }
                }
                // next element
                xpp.next()
            }
            Log.d(LOG_TAG, "END_DOCUMENT")
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun showDataFromXml() {
        try {
            var tmp: String
            val xpp: XmlPullParser = R.xml.data.prepareXpp()
            while (xpp.eventType != XmlPullParser.END_DOCUMENT) {
                when (xpp.eventType) {
                    XmlPullParser.START_DOCUMENT -> Log.d(LOG_TAG, "START_DOCUMENT")
                    XmlPullParser.START_TAG -> {
                        Log.d(LOG_TAG, "START_TAG: name = " + xpp.name
                                + ", depth = " + xpp.depth + ", attrCount = "
                                + xpp.attributeCount)
                        tmp = ""
                        var i = 0
                        while (i < xpp.attributeCount) {
                            tmp = (tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ")
                            i++
                        }
                        if (!TextUtils.isEmpty(tmp)) Log.d(LOG_TAG, "Attributes: $tmp")
                    }
                    XmlPullParser.END_TAG -> Log.d(LOG_TAG, "END_TAG: name = " + xpp.name)
                    XmlPullParser.TEXT -> Log.d(LOG_TAG, "text = " + xpp.text)
                    else -> {
                    }
                }
                // next element
                xpp.next()
            }
            Log.d(LOG_TAG, "END_DOCUMENT")
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun Int.prepareXpp(): XmlPullParser {
        return resources.getXml(this)
    }


    private fun String.prepareXpp(): XmlPullParser {
        // получаем фабрику
        val factory: XmlPullParserFactory = XmlPullParserFactory.newInstance()
        // включаем поддержку namespace (по умолчанию выключена)
        factory.isNamespaceAware = true
        // создаем парсер
        val xpp = factory.newPullParser()
        // даем парсеру на вход Reader
        xpp.setInput(StringReader(this))
        return xpp
    }
}
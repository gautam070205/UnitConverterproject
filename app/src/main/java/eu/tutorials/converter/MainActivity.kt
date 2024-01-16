package eu.tutorials.converter

import android.os.Bundle
//import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.converter.ui.theme.ConverterTheme
//import eu.tutorials.myapplication.ui.theme.MyApplicationTheme

//import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
//import kotlin.time.times
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConverterTheme{
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    Unitconverter()
                }
            }
        }
    }
}
@Composable
fun Unitconverter () {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember {mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnity by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor =remember{ mutableStateOf(0.01) }  //double data type
    val oConversionFactor= remember{ mutableStateOf(1.000) }
    fun convertUnits() {
        val inputValueDouble=inputValue.toDoubleOrNull()?: 0.0  // elvis operator smart very short if statemnet  qwe-->0.0
        val result=(inputValueDouble * conversionFactor.value *100.0/oConversionFactor.value).roundToInt()/100.00
        outputValue=result.toString()

    }
    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement= Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        //here all the ui elements will be stacked below each other
        Text(text = "Unit Converter",style=MaterialTheme.typography.headlineLarge)
        Spacer(modifier=Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {

            inputValue=it
            convertUnits()
        },
            label = { Text(text = "Enter Value")}    )
        //{....}-->here goes what should happen when the value of ouitlined text fireld changes.......
        Spacer(modifier=Modifier.height(16.dp))
        Row {

//            val context=LocalContext.current
            Box {
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow down ")
                }

                DropdownMenu(expanded=iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpanded=false
                        inputUnit="Centimeters"
                        conversionFactor.value=0.01
                        convertUnits()

                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded=false
                        inputUnit="Meters"
                        conversionFactor.value= 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded=false
                        inputUnit="Feet"
                        conversionFactor.value=0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = { iExpanded=false
                        inputUnit="Millimeters"
                        conversionFactor.value=0.001
                        convertUnits() })

                }
            }
            Spacer(modifier=Modifier.width(
                16.dp))
            Box {
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnity)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow down ")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpanded=false
                        outputUnity="Centimeters"
                        oConversionFactor.value=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded=false
                        outputUnity="Meters"
                        oConversionFactor.value=1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded=false
                        outputUnity="Feet"
                        oConversionFactor.value=0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        oExpanded=false
                        outputUnity="Millimeters"
                        oConversionFactor.value=0.001
                        convertUnits()
                    })

                }

            }
        }
        Spacer(modifier=Modifier.height(16.dp))
        Text(text = "Results:$outputValue $outputUnity",
            style =MaterialTheme.typography.headlineMedium)    //anonmymous functiom is basically a function that does not have name
    }

}
@Preview(showBackground = true)
@Composable
fun UnitconverterPreview() {
    Unitconverter()
}
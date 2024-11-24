package kg.iuca.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kg.iuca.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var displayText by remember { mutableStateOf("") }
    var num1 by remember { mutableStateOf<Double?>(null) }
    var num2 by remember { mutableStateOf<Double?>(null) }
    var operation by remember { mutableStateOf<String?>(null) }

    fun onButtonClick(value: String) {
        when (value) {
            "+", "-", "*", "/" -> {
                num1 = displayText.toDoubleOrNull()
                operation = value
                displayText = ""
            }
            "=" -> {
                num2 = displayText.toDoubleOrNull()
                if (num1 != null && num2 != null && operation != null) {
                    displayText = when (operation) {
                        "+" -> (num1!! + num2!!).toString()
                        "-" -> (num1!! - num2!!).toString()
                        "*" -> (num1!! * num2!!).toString()
                        "/" -> if (num2 != 0.0) (num1!! / num2!!).toString() else "Ошибка"
                        else -> ""
                    }
                    num1 = null
                    num2 = null
                    operation = null
                }
            }
            "C" -> {
                displayText = ""
                num1 = null
                num2 = null
                operation = null
            }
            else -> displayText += value
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = displayText,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("C", "0", "=", "+")
        )

        buttons.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { label ->
                    Button(
                        onClick = { onButtonClick(label) },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                    ) {
                        Text(label)
                    }
                }
            }
        }
    }
}

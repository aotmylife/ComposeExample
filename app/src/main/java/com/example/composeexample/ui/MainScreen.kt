package com.example.composeexample.ui

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeexample.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val chocoBoCharacters = listOf(
        "chocobo" to R.drawable.chocobo,
        "Mog" to R.drawable.mog,
        "Golem" to R.drawable.golem,
        "Goblin" to R.drawable.goblin,
        "Black Mage (Black Magician)" to R.drawable.blackmage,
        "White Mage" to R.drawable.white_magic,
        "Chubby Chocobo" to R.drawable.chubby,
        "Behemoth" to R.drawable.behemoth,
        "Bahamut" to R.drawable.bahamut,
        "Squall Leonhart" to R.drawable.squall_leonhart
    )

    var inputText by remember { mutableStateOf("") }

    val filteredList = chocoBoCharacters.filter { it.first.contains(inputText, ignoreCase = true) }

    val focusManager = LocalFocusManager.current

    Surface(
        // EdgeToEdge
        modifier = Modifier
            .systemBarsPadding()
    ) {

        Column(
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focusManager.clearFocus()
                }
        ) {
            TopAppBar(
                title = { Text("Chocobo App") },
                navigationIcon = {
                    IconButton(onClick = {
                        Toast.makeText(context, "Back", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )

            TextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = {
                    Text(
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.Serif
                        ),
                        text = "Search",
                        fontSize = 32.sp
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            if (filteredList.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Medium,
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily.Serif
                        ),
                        text = "not found",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Center),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            } else {
                LazyVerticalGrid(
                    modifier = Modifier.padding(top = 8.dp),
                    columns = GridCells.Adaptive(120.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp)
                ) {
                    if (filteredList.isNotEmpty()) {
                        itemsIndexed(filteredList) { _, items ->
                            Card(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .wrapContentHeight()
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Color.DarkGray)
                                    .clickable {
                                        focusManager.clearFocus()
                                        Toast
                                            .makeText(context, items.first, Toast.LENGTH_LONG)
                                            .show()
                                    }
                            ) {
                                Column(modifier = Modifier.animateContentSize()) {
                                    Image(
                                        painter = painterResource(id = items.second),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .height(180.dp)
                                            .width(180.dp)
                                            .fillMaxWidth()
                                    )

                                    Text(
                                        style = TextStyle(
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Medium,
                                            fontStyle = FontStyle.Italic,
                                            fontFamily = FontFamily.Serif
                                        ),
                                        text = items.first,
                                        color = Color.White,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(color = Color.DarkGray)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
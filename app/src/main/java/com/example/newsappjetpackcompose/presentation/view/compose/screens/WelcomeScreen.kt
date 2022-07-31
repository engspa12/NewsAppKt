package com.example.newsappjetpackcompose.presentation.view.compose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.global.Constants
import com.example.newsappjetpackcompose.presentation.view.compose.theme.NewsAppKtTheme

@Composable
fun WelcomeScreen(
    searchType: String,
    searchInput: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    WelcomeContent(
        searchType = searchType,
        searchInput = searchInput,
        onSearchInputChanged = {
            onSearchInputChanged(it)
        },
        onSearchButtonClicked = {
            onSearchButtonClicked()
        },
        modifier = modifier
    )
}

@Composable
fun WelcomeContent(
    searchType: String,
    searchInput: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.welcome_background))
            .padding(horizontal = 20.dp)
    ) {
        item {
            Text(
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W800,
                fontStyle = FontStyle.Italic,
                color = colorResource(id = R.color.white),
                text = stringResource(id = R.string.welcome_title)
            )
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.daily_news),
                contentDescription = stringResource(id = R.string.welcome_image_content_description),
                modifier = Modifier.requiredSize(width = 250.dp, height = 150.dp)
            )
        }
        item {
            Text(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                color = colorResource(id = R.color.white),
                text = stringResource(id = R.string.welcome_message),
                modifier = Modifier.padding(horizontal = 28.dp)
            )
        }
        item {
            TextField(
                value = searchInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.text_color_edit_text),
                    fontSize = 28.sp,
                    textAlign = TextAlign.Center
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_hint),
                        color = colorResource(id = R.color.text_color_hint_edit_text),
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                onValueChange = {
                    onSearchInputChanged(it)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.welcome_background),
                    focusedIndicatorColor = colorResource(id = R.color.white),
                    cursorColor = colorResource(id = R.color.white)
                )
            
            )
        }
        item {
            Button(
                onClick = {
                    onSearchButtonClicked()
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.button_background)
                )
            ) {
                if(searchType == Constants.RELEVANCE_SEARCH_TYPE)
                    Text(
                        text = stringResource(id = R.string.search_relevant_news_text),
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        color = colorResource(id = R.color.white)
                    )
                else
                    Text(
                        text = stringResource(id = R.string.search_latest_news_text),
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        color = colorResource(id = R.color.white)
                    )
            }
        }
    }
}

@Preview
@Composable
fun WelcomePreview(){
    NewsAppKtTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            WelcomeScreen(
                searchType = "",
                searchInput = "",
                onSearchInputChanged = {

                },
                onSearchButtonClicked = {

                }
            )
        }
    }
}
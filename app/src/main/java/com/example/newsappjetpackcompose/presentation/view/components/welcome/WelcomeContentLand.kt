package com.example.newsappjetpackcompose.presentation.view.components.welcome

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.global.Constants
import com.example.newsappjetpackcompose.presentation.view.theme.NewsAppKtTheme
import com.example.newsappjetpackcompose.presentation.view.theme.fontSize
import com.example.newsappjetpackcompose.presentation.view.theme.spacing

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WelcomeContentLand(
    searchType: String,
    searchInput: String,
    onSearchInputChanged: (String) -> Unit,
    onSearchButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
    ) {
        Text(
            fontSize = MaterialTheme.fontSize.size30,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.dimen18),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W800,
            fontStyle = FontStyle.Italic,
            color = colorResource(id = R.color.white),
            text = stringResource(id = R.string.welcome_title)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.daily_news),
                contentDescription = stringResource(id = R.string.welcome_image_content_description),
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                fontSize = MaterialTheme.fontSize.size20,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                color = colorResource(id = R.color.white),
                text = stringResource(id = R.string.welcome_message),
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.dimen20)
                    .weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
        ) {
            TextField(
                value = searchInput,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.dimen30)
                    .weight(1f),
                textStyle = TextStyle(
                    color = colorResource(id = R.color.text_color_edit_text),
                    fontSize = MaterialTheme.fontSize.size28,
                    textAlign = TextAlign.Center
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_hint),
                        color = colorResource(id = R.color.text_color_hint_edit_text),
                        fontSize = MaterialTheme.fontSize.size28,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                },
                onValueChange = {
                    onSearchInputChanged(it)
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = R.color.welcome_background),
                    focusedIndicatorColor = colorResource(id = R.color.white),
                    cursorColor = colorResource(id = R.color.white)
                )
            )
            Button(
                onClick = {
                    onSearchButtonClicked()
                },
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.dimen30)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.button_background)
                )
            ) {
                if(searchType == Constants.RELEVANCE_SEARCH_TYPE)
                    Text(
                        text = stringResource(id = R.string.search_relevant_news_text),
                        fontSize = MaterialTheme.fontSize.size20,
                        fontStyle = FontStyle.Italic,
                        color = colorResource(id = R.color.white)
                    )
                else
                    Text(
                        text = stringResource(id = R.string.search_latest_news_text),
                        fontSize = MaterialTheme.fontSize.size20,
                        fontStyle = FontStyle.Italic,
                        color = colorResource(id = R.color.white)
                    )
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, device = Devices.AUTOMOTIVE_1024p, widthDp = 1024)
@Composable
fun WelcomeLand(){
    NewsAppKtTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            WelcomeContentLand(
                searchType = Constants.RELEVANCE_SEARCH_TYPE,
                searchInput = "abcd",
                onSearchInputChanged = {

                },
                onSearchButtonClicked = {

                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.welcome_background))
                    .padding(horizontal = MaterialTheme.spacing.dimen20)
            )
        }
    }
}
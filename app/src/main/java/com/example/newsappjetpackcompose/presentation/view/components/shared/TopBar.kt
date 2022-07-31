package com.example.newsappjetpackcompose.presentation.view.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.global.Constants

@OptIn(ExperimentalTextApi::class)
@Composable
fun TopBar(
    showMenu: Boolean,
    titleTopBar: String,
    navigationType: Constants.NavType,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onDismissMenu: () -> Unit,
    onMenuIconClick: () -> Unit,
    onMenuItemClick: (String) -> Unit
){
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSecondary,
        contentPadding = PaddingValues(start = 18.dp),
        elevation = 0.dp,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            when (navigationType) {
                Constants.NavType.NAV_MAIN -> {}
                Constants.NavType.NAV_DETAILS -> {
                    Icon(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Go back to main",
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
            Text(
                modifier = Modifier.wrapContentHeight(),
                text = titleTopBar,
                fontSize = 22.sp,
                color = MaterialTheme.colors.onSecondary,
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        lineHeight = 22.sp,
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    )
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            when (navigationType) {
                Constants.NavType.NAV_MAIN -> {
                    Row(
                        modifier = Modifier.padding(end = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 16.dp, end = 12.dp)
                                .clickable
                                {
                                    onMenuIconClick()
                                },
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More options",
                            tint = MaterialTheme.colors.onSecondary
                        )

                        DropdownMenu(
                            modifier = Modifier.background(color = MaterialTheme.colors.background),
                            expanded = showMenu,
                            onDismissRequest = { onDismissMenu() })
                        {
                            DropdownMenuItem(
                                onClick = {
                                    onMenuItemClick(Constants.NEWEST_SEARCH_TYPE)
                                }
                            ) {
                                Text(text = stringResource(id = R.string.search_latest_news_text), color = MaterialTheme.colors.onBackground)
                            }
                            DropdownMenuItem(
                                onClick = {
                                    onMenuItemClick(Constants.RELEVANCE_SEARCH_TYPE)
                                }
                            ) {
                                Text(text = stringResource(id = R.string.search_relevant_news_text), color = MaterialTheme.colors.onBackground)
                            }
                        }
                    }
                }
                Constants.NavType.NAV_DETAILS -> {}
            }

        }
    }
}
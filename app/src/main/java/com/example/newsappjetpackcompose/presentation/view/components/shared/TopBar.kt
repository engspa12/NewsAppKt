package com.example.newsappjetpackcompose.presentation.view.components.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
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
import androidx.compose.ui.unit.sp
import com.example.newsappjetpackcompose.R
import com.example.newsappjetpackcompose.global.Constants
import com.example.newsappjetpackcompose.presentation.view.theme.spacing

@OptIn(ExperimentalTextApi::class)
@Composable
fun TopBar(
    showMenu: Boolean,
    titleTopBar: String,
    navigationType: Constants.NavType,
    goBackToMain: () -> Unit,
    modifier: Modifier = Modifier,
    onDismissMenu: () -> Unit,
    onMenuIconClick: () -> Unit,
    onMenuItemClick: (String) -> Unit
){
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSecondary,
        contentPadding = PaddingValues(start = MaterialTheme.spacing.dimen18),
        elevation = MaterialTheme.spacing.dimen0,
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
                            .padding(end = MaterialTheme.spacing.dimen12)
                            .clickable {
                                goBackToMain()
                            },
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.go_back_to_main),
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
                        modifier = Modifier.padding(end = MaterialTheme.spacing.dimen4),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(top = MaterialTheme.spacing.dimen16, bottom = MaterialTheme.spacing.dimen16, end = MaterialTheme.spacing.dimen12)
                                .clickable
                                {
                                    onMenuIconClick()
                                },
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = stringResource(R.string.more_options),
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
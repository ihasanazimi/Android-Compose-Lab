package ir.hasanazimi.android_lab.presentation.ui_kit.snack_bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import ir.hasanazimi.android_lab.R
import ir.hasanazimi.android_lab.presentation.theme.AppTheme

@Composable
fun CustomSnackBar(
    textMessage: String,
    isSuccessfully: Boolean? = null
) {
    AppTheme {
        Card(
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp, top = 12.dp)
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isSuccessfully != null && isSuccessfully) {
                    Color.DarkGray
                } else if (isSuccessfully != null && isSuccessfully.not()) {
                    Color.DarkGray
                } else {
                    Color.DarkGray
                }
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (isSuccessfully != null) {
                    Image(
                        painter = painterResource(if (isSuccessfully) R.drawable.baseline_done_24 else R.drawable.outline_close_24),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .background(
                                color = (if (isSuccessfully) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.background).copy(
                                    alpha = 0.2f
                                ), CircleShape
                            )
                            .size(20.dp)
                            .padding(if (isSuccessfully) 3.dp else 0.dp),
                        colorFilter = ColorFilter.tint(
                            if (isSuccessfully) {
                                MaterialTheme.colorScheme.background
                            } else {
                                MaterialTheme.colorScheme.background
                            }
                        ),
                        contentScale = ContentScale.FillBounds
                    )
                }

                Text(
                    modifier = Modifier.padding(
                        bottom = 16.dp,
                        top = 16.dp,
                        start = 16.dp,
                        end = 14.dp
                    ),
                    text = textMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = TextUnit(24f, TextUnitType.Sp),
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CustomSnackBarPreview() {

    val text =
        "متن تستی است این متن تستی است "

    LazyColumn {
        item {
            AppTheme {
                CustomSnackBar(
                    textMessage = text,
                    isSuccessfully = true
                )
            }
        }

        item {
            AppTheme {
                CustomSnackBar(
                    textMessage = text,
                    isSuccessfully = false
                )
            }
        }

        item {
            AppTheme {
                CustomSnackBar(
                    textMessage = text
                )
            }
        }
    }
}


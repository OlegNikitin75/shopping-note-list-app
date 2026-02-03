package alex.dev.shoppingnotelistapp.presentation.ui.components

import alex.dev.shoppingnotelistapp.R
import alex.dev.shoppingnotelistapp.presentation.ui.components.theme.RobotoFlex
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ShoppingListItem(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "List_2",
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 18.sp,
                        fontFamily = RobotoFlex,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "31/01/26 20:00",
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.6f),
                        fontFamily = RobotoFlex,
                        fontSize = 12.sp,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .padding(end = 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "12/6",
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 4.dp)
                                .widthIn(min = 40.dp),
                            fontFamily = RobotoFlex,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .padding(5.dp)
                            .size(28.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit_list),
                            contentDescription = "Edit list",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .padding(5.dp)
                            .size(28.dp),
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_remove_list),
                            contentDescription = "Remove list",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Green)
                .padding(top = 5.dp)
        )
    }
}


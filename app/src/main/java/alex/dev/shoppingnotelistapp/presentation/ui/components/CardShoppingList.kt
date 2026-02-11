package alex.dev.shoppingnotelistapp.presentation.ui.components

import alex.dev.shoppingnotelistapp.R
import alex.dev.shoppingnotelistapp.data.data_source.local.room.entities.ShoppingList
import alex.dev.shoppingnotelistapp.presentation.ui.theme.RobotoFlex
import alex.dev.shoppingnotelistapp.presentation.utils.formatDate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardShoppingList(
    item: ShoppingList,
    modifier: Modifier = Modifier,
    isExpanded:Boolean = false,
    onExpandedChange: (Boolean) -> Unit,
    onDelete: (ShoppingList) -> Unit,
    onEdit: (ShoppingList) -> Unit,
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Box {
            Column {
                Row(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = item.name,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 16.sp,
                            fontFamily = RobotoFlex,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = formatDate(item.createdAt),
                            color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                            fontFamily = RobotoFlex,
                            fontSize = 12.sp,
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { onExpandedChange(true) },
                            modifier = Modifier
                                .padding(5.dp)
                                .size(28.dp),
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_sub_menu),
                                contentDescription = "Меню",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                if (item.allItemsCount > 0) {
                    Row() {
                        LinearProgressIndicator(
                            progress = { item.allSelectedItemsCount.toFloat() / item.allItemsCount },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )

                        Text(
                            text = "${item.allSelectedItemsCount}/${item.allItemsCount}",
                            modifier = Modifier
                                .padding(vertical = 2.dp, horizontal = 4.dp)
                                .widthIn(min = 40.dp),
                            fontFamily = RobotoFlex,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { onExpandedChange(false) },
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Редактировать",
                            fontFamily = RobotoFlex
                        )
                    },
                    onClick = {
                        onExpandedChange(false)
                        onEdit(item)
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit_list),
                            contentDescription = null
                        )
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            text = "Удалить",
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = RobotoFlex
                        )
                    },
                    onClick = {
                        onExpandedChange(false)
                        onDelete(item)
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_remove_list),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                )
            }
        }
    }
}


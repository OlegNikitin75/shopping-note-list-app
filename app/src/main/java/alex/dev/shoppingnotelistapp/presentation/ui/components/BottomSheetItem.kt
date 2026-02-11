package alex.dev.shoppingnotelistapp.presentation.ui.components

import alex.dev.shoppingnotelistapp.presentation.ui.theme.RobotoFlex
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomSheetItem(
    iconId: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Icon(painter = painterResource(iconId), contentDescription = title)
        Text(text = title, fontFamily = RobotoFlex, fontSize = 16.sp)
    }
}
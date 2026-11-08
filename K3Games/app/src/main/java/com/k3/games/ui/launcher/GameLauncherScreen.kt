package com.k3.games.ui.launcher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k3.games.ui.theme.K3Background
import com.k3.games.ui.theme.K3Gold
import com.k3.games.ui.theme.K3GoldDeep
import com.k3.games.ui.theme.K3Orange
import com.k3.games.ui.theme.K3Panel
import com.k3.games.ui.theme.K3Red
import com.k3.games.ui.theme.K3TextPrimary
import com.k3.games.ui.theme.K3TextSecondary

private data class GameCardModel(
    val name: String,
    val packageName: String,
    val lastPlayedMinutesAgo: Int,
    val totalPlayMinutes: Int,
    val iconColor: Color,
)

@Composable
fun GameLauncherScreen() {
    val gameCards = remember {
        listOf(
            GameCardModel("PUBG Mobile", "com.tencent.ig", 26, 1280, K3Gold),
            GameCardModel("Genshin Impact", "com.miHoYo.GenshinImpact", 67, 980, K3Orange),
            GameCardModel("Asphalt 9", "com.gameloft.android.A9LH", 118, 870, K3Red),
            GameCardModel("Honkai: Star Rail", "com.HoYoverse.hkrpgoversea", 314, 600, K3GoldDeep),
        )
    }

    var boostEnabled by remember { mutableStateOf(true) }
    var gridMode by remember { mutableStateOf(false) }
    var selectedPerformance by remember { mutableStateOf("Balanced") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(K3Background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TopBar()

        StatusStrip()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Performance mode", color = K3TextPrimary, fontWeight = FontWeight.SemiBold)
            OutlinedButton(onClick = { boostEnabled = !boostEnabled }) {
                Text(if (boostEnabled) "Boost active" else "Boost off")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("Battery", "Balanced", "Performance", "K3 Extreme").forEach { mode ->
                Button(
                    onClick = { selectedPerformance = mode },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (mode == selectedPerformance) K3Gold else K3Panel,
                        contentColor = if (mode == selectedPerformance) K3Background else K3TextPrimary,
                    ),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Text(mode)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Game Space", color = K3TextPrimary, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Button(onClick = { gridMode = !gridMode }, colors = ButtonDefaults.buttonColors(containerColor = K3Panel)) {
                Text(if (gridMode) "List view" else "Grid view")
            }
        }

        if (gridMode) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                gameCards.chunked(2).forEach { rowGames ->
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        rowGames.forEach { game ->
                            GameCard(game = game, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        } else {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(gameCards) { game ->
                    GameCard(game = game, modifier = Modifier.widthCard())
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("One-tap Boost", color = K3TextPrimary, fontWeight = FontWeight.SemiBold)
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = K3Red),
            ) {
                Text("Clear RAM")
            }
        }
    }
}

@Composable
private fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(brush = Brush.linearGradient(listOf(K3Gold, K3Red)), shape = CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text("K3", color = K3Background, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text("K3 Games", color = K3TextPrimary, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = K3Gold)
    }
}

@Composable
private fun StatusStrip() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = K3Panel),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            StatChip(icon = Icons.Default.BatteryChargingFull, label = "88%", tint = K3Gold)
            StatChip(icon = Icons.Default.SignalCellularAlt, label = "62°C", tint = K3Orange)
            StatChip(icon = Icons.Default.Memory, label = "4.1GB", tint = K3Gold)
            StatChip(icon = Icons.Default.Bolt, label = "120Hz", tint = K3Red)
        }
    }
}

@Composable
private fun StatChip(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, tint: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = label, tint = tint, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.size(6.dp))
        Text(label, color = K3TextPrimary, fontSize = 12.sp)
    }
}

@Composable
private fun GameCard(game: GameCardModel, modifier: androidx.compose.ui.Modifier) {
    Card(
        modifier = modifier.height(200.dp),
        colors = CardDefaults.cardColors(containerColor = K3Panel),
        shape = RoundedCornerShape(20.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.linearGradient(listOf(game.iconColor, K3Background)))
                .padding(14.dp),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(game.iconColor, shape = RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Default.Games, contentDescription = game.name, tint = K3TextPrimary)
                    }
                    Text("${game.lastPlayedMinutesAgo}m", color = K3TextSecondary)
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(game.name, color = K3TextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text("Play time: ${game.totalPlayMinutes / 60}h ${game.totalPlayMinutes % 60}m", color = K3TextSecondary, fontSize = 12.sp)
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = K3Gold),
                ) {
                    Text("PLAY", color = K3Background, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

private fun Modifier.widthCard(): Modifier = this.then(Modifier.width(230.dp))

package com.example.composetutorial

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column{
                Column(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)){
                    TopBar()
                    SearchBar()
                    NamesBar()
                    LovesArea()
                }

                NavBar()
            }

        }
    }
}


@Composable
fun NavBar(){
    Row(
        Modifier
            .height(84.dp)
            .padding(16.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        NavItem(R.drawable.ic_home, "首頁", Green)
        NavItem(R.drawable.ic_tag, "標籤", Gray)
        NavItem(R.drawable.ic_calender, "日曆", Gray)
        NavItem(R.drawable.ic_me, "我", Gray)
    }
}
@Composable
fun RowScope.NavItem(@DrawableRes iconRes: Int, description: String, tint: Color){
    Button(
        onClick = {},
        Modifier
            .weight(1f)
            .fillMaxHeight(),
        shape = RectangleShape,
        colors = ButtonDefaults.outlinedButtonColors()
    ){
        Icon(painterResource(iconRes), description,
            Modifier
                .size(24.dp)
                .weight(1f),
            tint = tint)
    }
}
@Composable
fun TopBar(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(28.dp, 28.dp, 28.dp, 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(painterResource(R.drawable.ic_thumbnail), "頭像",
            Modifier
                .clip(CircleShape)
                .size(64.dp)
                .clickable { })
        Column(
            Modifier
                .padding(start = 14.dp)
                .weight(1f)){
            Text("歡迎回來!", fontSize = 14.sp, color = Gray)
            Text("luke", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Surface(Modifier.clip(CircleShape), color = Color(0xFFFFB6C1)){
            Image(painterResource(R.drawable.ic_notification), "通知",
                Modifier
                    .padding(10.dp)
                    .size(32.dp)
                    .clickable { })

        }
    }
}
@Composable
fun SearchBar(){
    Row(
        Modifier
            .padding(24.dp, 2.dp, 24.dp, 6.dp)
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ){
        var searchText by remember { mutableStateOf("") }
        BasicTextField(value = searchText, onValueChange = {searchText = it } ,
            Modifier
                .padding(start = 24.dp)
                .weight(1f),
            textStyle = TextStyle(fontSize = 15.sp)
        ){
            if(searchText.isEmpty()){
                Text("請輸入搜尋內容", color = Color(0xffb4b4b4), fontSize = 15.sp)
            }
            it()
        }
        Box(
            Modifier
                .padding(6.dp)
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color(0xfffa9e51))
        ){
            Icon(painterResource(R.drawable.search), "搜索",
                Modifier
                    .size(24.dp)
                    .align(Alignment.Center), tint = Color.White
            )
        }
    }
}
@Composable
fun NamesBar(){
    val names = listOf("街舞", "歌手", "實況主", "藝術創作", "音樂", "體育")
    var selected by remember {mutableStateOf(0)}
    LazyRow(Modifier.padding(0.dp, 8.dp), contentPadding = PaddingValues(12.dp, 0.dp)){
        itemsIndexed(names){index, name ->
            Column(
                Modifier
                    .padding(12.dp, 4.dp)
                    .width(IntrinsicSize.Max)){
                Text(name, fontSize = 15.sp,
                    color = if (index == selected)Color(0xfffa9e51) else Color(0xffb4b4b4)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .height(2.dp)
                        .clip(RoundedCornerShape(1.dp))
                        .background(if (index == selected) Color(0xfffa9e51) else Color.Transparent)
                )
            }
        }
    }
}

@Composable
fun LovesArea(){
    val titles = listOf("街舞大賽", "老師開課","活動資訊")
    val newsPicture = listOf(R.drawable.oldschoolnight, R.drawable.hozinws, R.drawable.danceevent)
    Column{
        Row(Modifier.padding(24.dp, 12.dp)
        ){
            Text("你愛看的", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Text("展開全部", fontSize = 15.sp, color = Color(0xffb4b4b4))
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(24.dp)
            ){
            itemsIndexed(titles){index, name ->
                Surface(Modifier.clip(RoundedCornerShape(10.dp)).clickable {  }, color = Color.White){
                    Column(Modifier.padding(10.dp, 10.dp).clip(RoundedCornerShape(10.dp))){
                        Image(painterResource(newsPicture[index]), "縮圖",
                            Modifier
                                .size(200.dp, 200.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            contentScale = ContentScale.FillBounds
                        )
                        Text(name, Modifier.align(Alignment.CenterHorizontally))
                    }
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTutorialTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background

        ) {
            NavBar()
        }
    }
}
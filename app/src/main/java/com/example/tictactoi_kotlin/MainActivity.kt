package com.example.tictactoi_kotlin


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoi_kotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    lateinit var playerOnePoints:TextView
    lateinit var playerTwoPoints:TextView

    var activePlayer:Boolean = true
    var checkPlay = true

    var playerOnePointsCount:Int = 0
    var playerTwoPointsCount:Int = 0
    var rountCount:Int = 0

    var gamePlay = mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    var winningStatus = mutableListOf(
            mutableListOf(0, 1, 2), mutableListOf(3, 4, 5), mutableListOf(6, 7, 8),
            mutableListOf(0, 3, 6), mutableListOf(1, 4, 7), mutableListOf(2, 5, 8),
            mutableListOf(0, 4, 8), mutableListOf(2, 4, 6)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        playerOnePoints = findViewById(R.id.pointsPlayer1)
        playerTwoPoints = findViewById(R.id.pointsPlayer2)
    }

    fun onClickButton(view: View){

        checkPlay = true

        var btnId = view.resources.getResourceEntryName(view.id)
        var playerPointer:Int = btnId.substring(btnId.length - 1, btnId.length).toInt()

        var v = view as Button

       if (activePlayer && v.text.toString().isNullOrBlank() && checkPlay) {
           view.text = "X"
           gamePlay[playerPointer] = 0
           rountCount++
       }else if( !activePlayer && v.text.toString().isNullOrBlank() && checkPlay) {
           view.text = "O"
           gamePlay[playerPointer] = 1
           rountCount++
       }else{
           checkPlay = false
           Toast.makeText(this, "The cell is full !", Toast.LENGTH_LONG).show()
       }


       if(checkWinner()){
                 if(activePlayer){
                         Toast.makeText(this, "player one winner", Toast.LENGTH_LONG).show()
                         playerOnePointsCount++
                         updatePlayerScore()
                         playAgain()
                 }else{
                     Toast.makeText(this, "player Two winner", Toast.LENGTH_LONG).show()
                     playerTwoPointsCount++
                     updatePlayerScore()
                     playAgain()
                 }
       }else if(rountCount == 9){
             Toast.makeText(this, "No Winner", Toast.LENGTH_LONG).show()
             playAgain()
       }else if(checkPlay){
             activePlayer =!activePlayer
       }
   }

    private fun checkWinner():Boolean{
       var winnerResult = false

        for( check in  winningStatus)
             if(((gamePlay[check[0]] == gamePlay[check[1]])&&
                     (gamePlay[check[1]] == gamePlay[check[2]]))  && gamePlay[check[0]]  != 2  )
                  return true

        return winnerResult
    }

    private fun updatePlayerScore(){
        playerOnePoints.text = playerOnePointsCount.toString()
        playerTwoPoints.text = playerTwoPointsCount.toString()
    }

    @SuppressLint("NewApi")
    fun playAgain(){
        rountCount = 0
        activePlayer = true
        checkPlay = true
        gamePlay = mutableListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

        for(i in 0..8){
            var name = "button_$i"
            var id = resources.getIdentifier(name , "id" , packageName)
            var bottonBoards:Button = this.requireViewById(id)
            bottonBoards.text = " "
        }

    }

     fun onClickResert(v: View?) {
        playAgain()
    }

    fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}







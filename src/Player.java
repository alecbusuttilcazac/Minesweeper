class Player{
    int score = 0;
    String nickname;

    int getScore()              {return score;}
    void setScore(int s)        {score = s;}
    void adjustScore(int s)     {score += s;}

    String getNickname()        {return nickname;}
    void setNickname(String n)  {nickname = n;}
}
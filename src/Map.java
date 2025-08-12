import java.util.*;

class Map {
    static ArrayList<Tile[]> rows = new ArrayList<Tile[]>();
    int sizeX, sizeY;
    
    // Initialise the map with a given size and number of bombs
    Map(int x, int y, int numBombs){
        System.out.println("> Map(x="+x+", y="+y+", numBombs="+numBombs+")\n");
        sizeX = x; 
        sizeY = y;
        
        for(int i=0; i<y; i++){
            rows.add(new Tile[x]); // Initialise each ArrayList 'column' with an array 'row'
            System.out.print("\033[1F\33[K");
            System.out.println("added tile[] "+i);
        }
        
        int c = 0;
        do{
            // Generate random x and y coordinates for randomised bomb placement
            int xPlacement = (int)(Math.random()*x);
            int yPlacement = (int)(Math.random()*y);
            
            System.out.println("Random bomb placement: bombNo="+c+", x="+xPlacement+", y="+yPlacement);
            
            // Only place a bomb if the tile is not already a BombTile
            if(!(getTile(xPlacement, yPlacement) instanceof BombTile)){
                setTile(xPlacement, yPlacement, new BombTile(Settings.getBombChar()));
                System.out.println("> setTile(" +xPlacement+ ", " +yPlacement+ ")");
                c++;
            }else System.out.print("\033[1F\33[K");
        }while(c < numBombs);
        
        int bombsAroundTile = 0;
        System.out.println();
        
        for(int j=0; j<y; j++){
            for(int i=0; i<x; i++){ 
                if(!(getTile(i, j) instanceof BombTile)){
                    
                    if(i != 0 && j != 0)            if(getTile(i-1, j-1)    instanceof BombTile) bombsAroundTile++; //1
                    if(j != 0)                      if(getTile(i,   j-1)    instanceof BombTile) bombsAroundTile++; //2
                    if(i != (x-1) && j != 0)        if(getTile(i+1, j-1)    instanceof BombTile) bombsAroundTile++; //3
                    //                                                                                                      1 2 3
                    if(i != 0)                      if(getTile(i-1, j)      instanceof BombTile) bombsAroundTile++; //4     4 X 5
                    if(i != (x-1))                  if(getTile(i+1, j)      instanceof BombTile) bombsAroundTile++; //5     6 7 8
                    //                                                                                                    
                    if(i != 0 && j != (y-1))        if(getTile(i-1, j+1)    instanceof BombTile) bombsAroundTile++; //6
                    if(j != (y-1))                  if(getTile(i,   j+1)    instanceof BombTile) bombsAroundTile++; //7
                    if(i != (x-1) && j != (y-1))    if(getTile(i+1, j+1)    instanceof BombTile) bombsAroundTile++; //8
                    
                    if(bombsAroundTile == 0) setTile(i, j, new EmptyTile("", 0));
                    else setTile(i, j, new EmptyTile(String.valueOf(bombsAroundTile), bombsAroundTile));
                    
                    System.out.println("> setTile("+i+", "+j+", "+String.valueOf(bombsAroundTile)+", "+Settings.getFlagChar()+")");
                    bombsAroundTile = 0;
                    
                }else System.out.println("Tried setTile("+i+", "+j+") but instanceof BombTile");
            }
            System.out.println();
        }
    }
    
    Tile getTile(int x, int y)              { return rows.get(y)[x]; }
    void setTile(int x, int y, Tile tile)   { rows.get(y)[x] = tile; }
    
    int getSizeX()  { return sizeX; }
    int getSizeY()  { return sizeY; }
}

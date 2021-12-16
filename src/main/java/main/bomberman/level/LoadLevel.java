package main.bomberman.level;

import main.bomberman.entities.Entity;
import main.bomberman.entities.character.enermy.*;
import main.bomberman.entities.tile.Brick;
import main.bomberman.entities.tile.Grass;
import main.bomberman.entities.tile.Wall;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadLevel {
    private static int _width, _height, _level = 1;
    private static char[][] _map;

    private static ArrayList<Entity> map = new ArrayList<>();
    private static ArrayList<Enemy> listEnemy = new ArrayList<>();

    public LoadLevel(){

    }

    public static void load(int level) {
        String path = ".\\res\\levels\\Level"+level+".txt";
        try{
            File file = new File(path);
            Scanner reader = new Scanner(file);

            _level = reader.nextInt();
            _height = reader.nextInt();
            _width = reader.nextInt();
            reader.nextLine();
            System.out.println(_level + " " + _height + " " + _width);
            _map = new char[_height][_width];
            for (int i = 0; i < _height; i++) {
                String line = reader.nextLine();
                for (int j = 0; j < _width; j++){
                    _map[i][j] = line.charAt(j);
                    System.out.print(_map[i][j]);
                }
                System.out.println("");
            }
            reader.close();

            setMap();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int get_level(){
        return _level;
    }

    public static char[][] get_map() {
        return _map;
    }

    public static int get_width(){
        return _width;
    }

    public static int get_height(){
        return _height;
    }

    private static void setMap(){
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++){
                Grass grass = new Grass();
                grass.setPosition(j*grass.getWidth(), i*grass.getHeight());

                switch (_map[i][j]){
                    case '#':
                        Wall wall = new Wall();
                        wall.setPosition(j*wall.getWidth(), i*wall.getHeight());
                        map.add(wall);
                        break;
                    case '*':
                        Brick brick = new Brick();
                        brick.setPosition(j*brick.getWidth(), i*brick.getHeight());
                        map.add(brick);
                        break;
                    case 's':
                        Brick speedItem = new Brick("speed");
                        speedItem.setPosition(j*speedItem.getWidth(), i*speedItem.getHeight());
                        map.add(speedItem);
                        break;
                    case 'f':
                        Brick flamesItem = new Brick("flames");
                        flamesItem.setPosition(j*flamesItem.getWidth(), i*flamesItem.getHeight());
                        map.add(flamesItem);
                        break;
                    case 'b':
                        Brick bombItem = new Brick("bomb");
                        bombItem.setPosition(j*bombItem.getWidth(), i*bombItem.getHeight());
                        map.add(bombItem);
                        break;
                    case '!':
                        Brick spaceShip = new Brick("spaceShip");
                        spaceShip.setPosition(j*spaceShip.getWidth(), i*spaceShip.getHeight());
                        map.add(spaceShip);
                        break;
                    case 'x':
                        Brick portal = new Brick("portal");
                        portal.setPosition(j*portal.getWidth(), i*portal.getHeight());
                        map.add(portal);
                        break;
                    case '1':
                        map.add(grass);
                        Balloon balloon = new Balloon();
                        balloon.setPosition(j*balloon.getWidth(), i*balloon.getHeight());
                        listEnemy.add(balloon);
                        break;
                    case '2':
                        map.add(grass);
                        Oneal oneal = new Oneal();
                        oneal.setPosition(j*oneal.getWidth(), i*oneal.getHeight());
                        listEnemy.add(oneal);
                        break;
                    case '3':
                        map.add(grass);
                        Doll doll = new Doll();
                        doll.setPosition(j*doll.getWidth(), i*doll.getHeight());
                        listEnemy.add(doll);
                        break;
                    case '4':
                        map.add(grass);
                        Kondoria kondoria = new Kondoria();
                        kondoria.setPosition(j*kondoria.getWidth(), i*kondoria.getHeight());
                        listEnemy.add(kondoria);
                        break;
                    case '5':
                        map.add(grass);
                        Minvo minvo = new Minvo();
                        minvo.setPosition(j*minvo.getWidth(), i*minvo.getHeight());
                        listEnemy.add(minvo);
                        break;
                    default:
                        map.add(grass);
                        break;
                }
            }
        }
    }


    public static ArrayList<Entity> getMap(){
        return map;
    }

    public static ArrayList<Enemy> getListEnemy(){
        return listEnemy;
    }
}

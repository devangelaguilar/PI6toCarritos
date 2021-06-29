<?php

class db{

    public $host = "localhost";
    public $user = "root";
    public $db = "cride";
    public $password = "";
    protected $con;

    public function __construct(){
        try{
            $this->con = new PDO("mysql:host=".host.";
            dbname=".db,user,password);
        }
        catch(PDOException $e){
            echo "Connection error: ".$e->getMessage();
        }
    }

}   

?>
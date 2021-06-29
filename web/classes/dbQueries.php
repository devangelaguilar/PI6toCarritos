<?php

class dbQueries extends db {
    public $result;
    
    public function Query($query, $params = []){
        if(empty($params)){
            $this->result = $this->con->prepare($query);
            return $this->result->execute();
        }
        else{
            $this->result = $this->con->prepare($query);
            return $this->result->execute($params);
        }
    }
    
    public function rowCount(){
        return $this->result->rowCount();
    }
    

    public function fetch(){
        return $this->result->fetch(PDO::FETCH_OBJ);
    }

    public function fetchAll(){
        return $this->result->fetchAll(PDO::FETCH_OBJ);
    }

}

?>
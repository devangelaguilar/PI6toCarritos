<?php

//error_reporting(0);
class validations extends dbQueries{

    public $errors = [];
    
    public function input($fieldName){
        if($_SERVER['REQUEST_METHOD'] == "POST" || $_SERVER['REQUEST_METHOD'] == "post"){
            return strip_tags(trim($_POST[$fieldName]));
        }
        else if($_SERVER['REQUEST_METHOD'] == "GET" || $_SERVER['REQUEST_METHOD'] == "get"){
            return strip_tags(trim($_GET[$fieldName]));
        }
    }

    public function validate($fieldName, $label, $rules){
        $allRules = explode('|', $rules);
        $value = $this->input($fieldName);
        $pattern = "/^[a-zA-Z ]+$/";

        if(in_array("required", $allRules)){
            if(empty($value)){
                return $this->errors[$fieldName] = $label . " no puede estar vacio.";
            }
        }

        if(in_array("alphabetic", $allRules)){
            if(!preg_match($pattern, $value)){
                return $this->errors[$fieldName] = $label . " tienen que ser caracteres alfabeticos.";
            }
        }

        if(in_array("min_len", $allRules)){
            $min_len_index = array_search("min_len", $allRules);
            $min_len_value_index = $min_len_index + 1;
            $min_len_value = $allRules[$min_len_value_index];
            if(strlen($value) < $min_len_value){
                return $this->errors[$fieldName] = $label . " debe ser " . $min_len_value . " valores de largo.";
            }
        }

        if(in_array("uniqueEmail", $allRules)){
            $uniqueEmail_index = array_search("uniqueEmail", $allRules);
            $table_index = $uniqueEmail_index + 1;
            $tableName = $allRules[$table_index];
            if($this->Query("SELECT * FROM " . $tableName . " WHERE " . $fieldName . " = ?",[$value])){
                if($this->rowCount() > 0){
                    return $this->errors[$fieldName] = $label . " ya existe.";
                }
            }
        }
    }

    public function run(){
        if(empty($this->errors)){
            return true;
        }
        else{
            return false;
        }
    }

    public function setValue($fieldName){
        if($_SERVER['REQUEST_METHOD'] == "POST" || $_SERVER['REQUEST_METHOD'] == "post"){
            if(isset($_POST[$fieldName])){
                return $_POST[$fieldName];
            }
        }
        else if($_SERVER['REQUEST_METHOD'] == "GET" || $_SERVER['REQUEST_METHOD'] == "get") {
            if(isset($_GET[$fieldName])){
                return $_GET[$fieldName];
            }
        }
    }

}

?>
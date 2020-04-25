/**
 * 定数はconst
 */
const NAME = "pompom";

/**
 * 1回しか呼ばれない関数は無名関数でよい。
 * 利点：名前をいちいち考えなくてよい。
 * @param {*} word 
 * @param {*} size 
 */
let onceTime = function(word, size) {

    if (typeof size != "number") {
        console.log("入力値が不正です。");
        console.log("word:" + word);
        console.log("size:" + size);
        return console.error();
    }

    if (size < 0) {
        console.log("入力値を見直してください。整数の入力が必要です。")
    }

    if (size == 0) {
        return null;
    }

    // sizeで指定された回数wordを横に連ねる。
    let result = new String(word);
    for (let i = 1; i < size; i++) {
        result += word;
    }
    return result;
}

/**
 * アロー関数
 * "function"の記載を省略してよい。
 * 引数リストのあとに"=>"で関数につなげる。
 */
let arrow1 = (word, size) => {
    return onceTime(word, size);
}

/**
 * アロー関数(1行版)
 * 補足：1行なら{}を省略してよい。returnも自動補完される。
 */
let arrow2 = (word, size) => onceTime(word, size);

/**
 * 指定された回数、numをかける。
 */
function moreTimes(num, frequency) {
    // 数字が入力されてなければアウト
    if (typeof num != "number" || typeof frequency != "number") {
        console.log("入力値が不正です。");
        console.log("num:" + num);
        console.log("frequency:" + frequency);
        return console.error();
    }

    // 負数は禁止
    if (frequency < 0) {
        console.log("入力値を見直してください。整数の入力が必要です。")
    } 
    
    // 0回ならnull
    if (frequency == 0) {
        return null;
    } 

    let result = num;
    for (let i = 1; i < frequency; i++) {
        result = multiply(result, num);
    }
    return result;
}

/**
 * 掛け算メソッド
 * @param {*} num1 
 * @param {*} num2 
 */
function multiply(num1, num2) {
    return num1 * num2;
}

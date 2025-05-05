package com.squareup.kotlinpoet;

import androidx.webkit.ProxyConfig;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001f\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!¨\u0006\""}, d2 = {"Lcom/squareup/kotlinpoet/KOperator;", "", "operator", "", "functionName", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getFunctionName$kotlinpoet", "()Ljava/lang/String;", "getOperator$kotlinpoet", "UNARY_PLUS", "PLUS", "UNARY_MINUS", "MINUS", "TIMES", "DIV", "REM", "PLUS_ASSIGN", "MINUS_ASSIGN", "TIMES_ASSIGN", "DIV_ASSIGN", "REM_ASSIGN", "INC", "DEC", "EQUALS", "NOT_EQUALS", "NOT", "RANGE_TO", "CONTAINS", "NOT_CONTAINS", "GT", "LT", "GE", "LE", "ITERATOR", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: KOperator.kt */
public enum KOperator {
    UNARY_PLUS("+", "unaryPlus"),
    PLUS("+", "plus"),
    UNARY_MINUS("-", "unaryMinus"),
    MINUS("-", "minus"),
    TIMES(ProxyConfig.MATCH_ALL_SCHEMES, "times"),
    DIV("/", TtmlNode.TAG_DIV),
    REM("%", "rem"),
    PLUS_ASSIGN("+=", "plusAssign"),
    MINUS_ASSIGN("-=", "minusAssign"),
    TIMES_ASSIGN("*=", "timesAssign"),
    DIV_ASSIGN("/=", "divAssign"),
    REM_ASSIGN("%=", "remAssign"),
    INC("++", "inc"),
    DEC("--", "dec"),
    EQUALS("==", "equals"),
    NOT_EQUALS("!=", "equals"),
    NOT("!", "not"),
    RANGE_TO("..", "rangeTo"),
    CONTAINS("in", "contains"),
    NOT_CONTAINS("!in", "contains"),
    GT(">", "compareTo"),
    LT("<", "compareTo"),
    GE(">=", "compareTo"),
    LE("<=", "compareTo"),
    ITERATOR("in", "iterator");
    
    private final String functionName;
    private final String operator;

    private KOperator(String str, String str2) {
        this.operator = str;
        this.functionName = str2;
    }

    public final String getOperator$kotlinpoet() {
        return this.operator;
    }

    public final String getFunctionName$kotlinpoet() {
        return this.functionName;
    }
}

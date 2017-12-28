package cn.javass.xgen.genconf.implementors.xmlimpl;

import cn.javass.xgen.genconf.constants.ExpressionEnum;
import cn.javass.xgen.genconf.constants.GenConfEnum;

public abstract class CommonBuilder<T> {
    protected abstract StringBuffer getBuilderBuffer();

    protected abstract T getBuilderClassInstance();

    public T addDot(){
        getBuilderBuffer().append(ExpressionEnum.dot.getExpr());
        return getBuilderClassInstance();
    }

    public T addSeparator(){
        getBuilderBuffer().append(ExpressionEnum.separator.getExpr());
        return getBuilderClassInstance();
    }
    /**
     * 获取$
     * @return
     */
    public T addDollar(){
        getBuilderBuffer().append(ExpressionEnum.dollar.getExpr());
        return getBuilderClassInstance();
    }
    /**
     * 获取[
     * @return
     */
    public T addOpenBracket(){
        getBuilderBuffer().append(ExpressionEnum.openBracket.getExpr());
        return getBuilderClassInstance();
    }
    /**
     * 获取]
     * @return
     */
    public T addCloseBracket(){
        getBuilderBuffer().append(ExpressionEnum.closeBracket.getExpr());
        return getBuilderClassInstance();
    }

    /**
     * 获取=
     * @return
     */
    public T addEqual(){
        getBuilderBuffer().append(ExpressionEnum.equal.getExpr());
        return getBuilderClassInstance();
    }
    /**
     * 获取,
     * @return
     */
    public T addComma(){
        getBuilderBuffer().append(ExpressionEnum.comma.getExpr());
        return getBuilderClassInstance();
    }
    /**
     * 获取xml
     * @return
     */
    public T addXml(){
        getBuilderBuffer().append(ExpressionEnum.xml.getExpr());
        return getBuilderClassInstance();
    }

    /**
     * 获取xml文件前缀路径
     * @return
     */

    public T addXmlFilePre(){
        getBuilderBuffer().append(ExpressionEnum.xgenconfxml.getExpr());
        return getBuilderClassInstance();
    }
    /**
     * 获取id
     * @return
     */
    public T addId(){
        getBuilderBuffer().append(GenConfEnum.id);
        return getBuilderClassInstance();
    }

    /**
     * 获取自定义值
     * @return
     */
    public T addOtherValue(String value){
        getBuilderBuffer().append(value);
        return getBuilderClassInstance();
    }



    public String build(){
        return getBuilderBuffer().toString();
    }
}

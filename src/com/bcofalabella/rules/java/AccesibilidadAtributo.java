package com.bcofalabella.rules.java;

import com.als.clases.AbstractJavaRule;
import com.als.core.RuleContext;
import com.als.jkingcore.ast.ASTCompilationUnit;
import com.als.jkingcore.ast.ASTFieldDeclaration;
import com.als.jkingcore.ast.JavaParserVisitorAdapter;

/**
 * COM.BCOFALABELLA.RULES.JAVA.ACCESIBILIDAD_ATRIBUTOS_INSTANCIA - ACCESIBILIDAD_ATRIBUTOS_INSTANCIA
 *
 * @author Created with Kiuwan Rule Developer
 * @version 1.0 (2018/10/29 09:37:41)
 */
public class AccesibilidadAtributo extends AbstractJavaRule {

  @Override
  protected void visit(ASTCompilationUnit astCompilationUnit, RuleContext ruleContext) {
    astCompilationUnit.jjtAccept(new FieldVisitor(), ruleContext);
  }

  
  class FieldVisitor extends JavaParserVisitorAdapter<RuleContext> {

    @Override
    public RuleContext visit(ASTFieldDeclaration fieldDeclaration, RuleContext ruleContext) {
      if (fieldDeclaration.isPublic()) {
        ruleContext.getReport()
            .addRuleViolation(
                createRuleViolation(ruleContext, fieldDeclaration.getBeginLine()));
      }
      return ruleContext;
    }
  }

}
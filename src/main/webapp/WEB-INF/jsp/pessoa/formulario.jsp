<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class="container">
   <div class="panel panel-primary">
    <div class="panel-heading"><h1>${pessoa.id == null ? 'Cadastrar' : 'Atualizar'} Pessoa</h1></div>
    <hr>
    <div class="panel-body">
     <form:form action="${formAction}" method="POST" modelAttribute="pessoa">
      <form:hidden path="id" />

      <div class="row g-3">
      <div class="col-md-4">
       <form:label path="nome">Nome</form:label>
       <form:input path="nome" type="text" class="form-control" required="required" />
       <form:errors path="nome" cssClass="text-warning" />
      </div>

      <div class="col-md-2">
         <form:label path="cpf">CPF</form:label>
         <form:input id="cpf"
                     path="cpf"
                     type="text"
                     class="form-control"/>
         <form:errors path="cpf" cssClass="text-warning" />
      </div>

      <div class="col-md-2">
          <form:label path="dataNascimento">Data de Nascimento</form:label>
          <form:input id="dataNascimento"
                      path="dataNascimento"
                      type="text"
                      class="form-control"/>
          <form:errors path="dataNascimento" cssClass="text-warning" />
      </div>
            <div class="col-md-2">
                <form:label path="funcionario">Funcionário</form:label>
                <form:select path="funcionario" class="form-control">
                    <form:option value="${null}">Selecione</form:option>
                    <form:option value="true">Sim</form:option>
                    <form:option value="false">Não</form:option>
                </form:select>
                <form:errors path="funcionario" cssClass="text-warning" />
            </div>

            <div class="col-md-2">
                            <form:label path="gerente">Gerente</form:label>
                            <form:select path="gerente" class="form-control">
                                <form:option value="${null}">Selecione</form:option>
                                <form:option value="true">Sim</form:option>
                                <form:option value="false">Não</form:option>
                            </form:select>
                            <form:errors path="gerente" cssClass="text-warning" />
                        </div>
      </div>
      <br>
      <div class="row">
          <div class="col-md-3" >
              <button type="submit" class="btn btn-primary">${pessoa.id == null ? 'Salvar' : 'Atualizar'}</button>
              <a type="button" class="btn btn-secondary" href="/portifolio/pessoa">Cancelar</a>
          </div>
      <div>

     </form:form>
    </div>
   </div>
</div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
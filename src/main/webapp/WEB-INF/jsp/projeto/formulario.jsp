<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/jsp/template/header.jsp"></jsp:include>

<div class="container">
   <div class="panel panel-primary">
    <div class="panel-heading"><h1>${projeto.id == null ? 'Cadastrar' : 'Atualizar'} Projeto</h1></div>
    <hr>
    <div class="panel-body">
     <form:form action="${formAction}" method="POST" modelAttribute="projeto">
      <form:hidden path="id" />

      <div class="row g-3">
      <div class="col-md-4">
       <form:label path="nome">Nome</form:label>
       <form:input path="nome" type="text" class="form-control" required="required" />
       <form:errors path="nome" cssClass="text-warning" />
      </div>

      <div class="col-md-4">
         <form:label path="gerente">Gerente</form:label>
         <form:select path="gerente.id" class="form-control" required="required">
             <form:option value="${null}">Selecione</form:option>
             <c:forEach items="${pessoas}" var="pessoa">
                 <form:option value="${pessoa.id}">${pessoa.nome}</form:option>
             </c:forEach>
         </form:select>
         <form:errors path="gerente.id" cssClass="text-warning" />
      </div>
       <div class="col-md-4">
             <form:label path="status">Status</form:label>
             <form:select path="status" class="form-control">
                 <form:option value="${null}">Selecione</form:option>
                 <form:option value="EM_ANALISE">
                    Em Análise
                 </form:option>
                 <form:option value="ANALISE_REALIZADA">
                    Análise Realizada
                 </form:option>
                 <form:option value="ANALISE_APROVADA">
                    Análise Aprovada
                 </form:option>
                 <form:option value="INICIADO">
                    Iniciado
                 </form:option>
                 <form:option value="PLANEJADO">
                    Planejado
                 </form:option>
                 <form:option value="EM_ANDAMENTO">
                    Em Andamento
                 </form:option>
                 <form:option value="ENCERRADO">
                    Encerrado
                 </form:option>
                 <form:option value="CANCELADO">
                    Cancelado
                 </form:option>
             </form:select>
             <form:errors path="status" cssClass="text-warning" />
        </div>
      </div>

      <div class="row g-3">
      <div class="col-md-2">
          <form:label path="dataInicio">Data de Início</form:label>
          <form:input id="dataInicio"
                      path="dataInicio"
                      type="text"
                      class="form-control"/>
          <form:errors path="dataInicio" cssClass="text-warning" />
      </div>

      <div class="col-md-2">
        <form:label path="dataPrevisaoFim">Data Previsão Fim</form:label>
        <form:input id="dataPrevisaoFim"
                    path="dataPrevisaoFim"
                    type="text"
                    class="form-control"/>
        <form:errors path="dataPrevisaoFim" cssClass="text-warning" />
      </div>
      <div class="col-md-2">
           <form:label path="dataFim">Data Fim</form:label>
           <form:input id="dataFim" path="dataFim" type="text" class="form-control" />
           <form:errors path="dataFim" cssClass="text-warning" />
      </div>
      <div class="col-md-6">
            <form:label path="descricao">Descrição</form:label>
            <form:textarea path="descricao" class="form-control" />
            <form:errors path="descricao" cssClass="text-warning" />
      </div>
      </div>

      <div class="row g-3">
        <div class="col-md-2">
         <form:label path="orcamento">Orçamento</form:label>
         <form:input path="orcamento" type="number" min="0" class="form-control" />
         <form:errors path="orcamento" cssClass="text-warning" />
        </div>

      <div class="col-md-2">
          <form:label path="risco">Risco</form:label>
          <form:select path="risco" class="form-control">
              <form:option value="${null}">Selecione</form:option>
              <form:option value="BAIXO">
                Baixo Risco
              </form:option>
              <form:option value="MEDIO">
                Médio Risco
              </form:option>
              <form:option value="ALTO">
                Alto Risco
              </form:option>
          </form:select>
          <form:errors path="risco" cssClass="text-warning" />
      </div>
      </div>

        <br>
      <div class="row">
          <div class="col-md-3" >
              <button type="submit"
                      class="btn btn-primary">
                      ${projeto.id == null ? 'Salvar' : 'Atualizar'}
              </button>
              <a type="button" class="btn btn-secondary" href="/portifolio/projeto">Cancelar</a>
          </div>
      <div>

     </form:form>
    </div>
   </div>
</div>

<jsp:include page="/WEB-INF/jsp/template/footer.jsp"></jsp:include>
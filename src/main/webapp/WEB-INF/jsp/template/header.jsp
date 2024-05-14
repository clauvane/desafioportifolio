<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Portifolio</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="/css/sticky-footer-navbar.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.8/jquery.inputmask.min.js" integrity="sha512-efAcjYoYT0sXxQRtxGY37CKYmqsFVOIwMApaEbrxJr4RwqVVGw8o+Lfh/+59TU07+suZn1BWq4fDl5fdgyCNkw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <style>
        .footer {
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
            background-color: #222222;
            color: white;
            height: 60px;
            text-align: center;
            margin: auto;
        }
    </style>
    <script>
    $(function(){
        $('#removerModal').on('show.bs.modal', function (event) {
          var button = $(event.relatedTarget)
          var link = button.data('link')
          $("#linkModalRemover").attr("href", link);
          var nome = button.data('nome')
          $('#nomeModalRemover').html(nome)
        })
    });

    $(document).ready(function(){
        $("#cpf").inputmask("999.999.999-99");
        $('input[type="text"][id^="data"]').inputmask("99/99/9999");
    });
    </script>
</head>
<body>

 <div class="modal fade" id="removerModal" tabindex="-1" aria-labelledby="removerModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="removerModalLabel">Confirmar Exclusão</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <b><span id="nomeModalRemover"></span></b>
        Você tem certeza que deseja continuar? Esta ação não poderá ser revertida.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <a id="linkModalRemover" type="button" class="btn btn-danger">Remover</a>
      </div>
    </div>
  </div>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" href="/home">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/portifolio/pessoa">Pessoas</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/portifolio/projeto">Projetos</a>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div>
   <c:if test="${msg != ''}">
      <c:if test="${tipoMsg == 'SUCESSO'}">
          <div class="alert alert-success" role="alert">
              ${msg}
          </div>
      </c:if>
      <c:if test="${tipoMsg == 'ERRO'}">
          <div class="alert alert-danger" role="alert">
              ${msg}
          </div>
      </c:if>
   </c:if>
</div>

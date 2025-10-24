// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

class UsuarioJaMatriculadoException(message: String) : Throwable(message)

class UsuarioNaoEncontradoException(message: String) : Throwable(message)

enum class Nivel {
    BASICO, INTERMEDIARIO, DIFICIL;

    companion object {
        fun getNivelFormatado(nivel: Nivel): String {
            return when (nivel) {
                BASICO -> "Básico"
                INTERMEDIARIO -> "Intermediário"
                DIFICIL -> "Difícil"
            }
        }
    }

}

data class Usuario(var nome: String, val matricula: Int)

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            throw UsuarioJaMatriculadoException("O usuário ${usuario.nome} já está matriculado.");
        }
        inscritos.add(usuario)
    }

    fun desmatricular(usuario: Usuario) {
        if (!inscritos.contains(usuario)) {
            throw UsuarioNaoEncontradoException("Não foi possível encontrar o usuário ${usuario.nome}")
        }
    }

    fun mostrarTodosAlunosMatriculados() {
        println("Alunos matriculados na Formação ${this.nome}: ")
        inscritos.forEach { aluno -> println("Nome: ${aluno.nome} - Matricula: ${aluno.matricula}") }
        println("======================================================================================")
    }

    fun mostrarTodosConteudosEducacionais() {
        println("Conteúdos Educacionas cadastrados na Formação ${this.nome}: ")
        conteudos.forEach { conteudo ->
            println(
                "Nome: ${conteudo.nome} - Duração: ${conteudo.duracao} - Nível: ${
                    Nivel.getNivelFormatado(
                        conteudo.nivel
                    )
                }"
            )
        }
        println("======================================================================================")
    }
}

fun main() {
    val conteudoEducacional = ConteudoEducacional("Fundamentos Kotlin", nivel = Nivel.INTERMEDIARIO);
    val listaConteudosEducacional = mutableListOf<ConteudoEducacional>();
    listaConteudosEducacional.add(conteudoEducacional)

    val formacao = Formacao("Kotlin", listaConteudosEducacional)

    val usuario = Usuario("João", 1)
    val usuario1 = Usuario("Pedro", 2)

    formacao.matricular(usuario)

//    formacao.desmatricular(usuario1)

    formacao.mostrarTodosAlunosMatriculados()
    formacao.mostrarTodosConteudosEducacionais()
}

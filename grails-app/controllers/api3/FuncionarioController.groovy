package api3

import groovy.json.JsonBuilder
import groovyx.net.http.RESTClient

class FuncionarioController {
    static responseFormats = ["json"]
    static defaultAction = "get"

    def list() {
        def api2BaseUrl = "http://localhost:8080/api2/"
        def client = new RESTClient(api2BaseUrl)
        def response = client.get(path: "funcionario/list")
        def jsonResponse = new JsonBuilder()
        jsonResponse {
            data response.data
        }
        render(contentType: 'application/json', text: jsonResponse.toString())
    }

    def get(Long id) {
        def api2BaseUrl = "http://localhost:8080/api2/"
        def client = new RESTClient(api2BaseUrl)
        def response = client.get(path: "funcionario/get/$id")
        def jsonResponse = new JsonBuilder()
        jsonResponse {
            data response.data
        }
        render(contentType: 'application/json', text: jsonResponse.toString())
    }

    def save() {
        if (request.method != "POST") {
            response.setStatus(405)
            render "Método não permitido para esta ação."
            return
        }
        try {
            def funcionarioData = request.JSON
            def api2BaseUrl = "http://localhost:8080/api2/"
            def client = new RESTClient(api2BaseUrl)
            def response = client.post(
                    path: "funcionario/save",
                    requestContentType: 'application/json',
                    body: funcionarioData
            )
            def jsonResponse = new JsonBuilder()
            jsonResponse {
                data response.data
            }
            render(contentType: 'application/json', text: jsonResponse.toString())
        } catch (Exception e) {
            renderError(e.message)
        }
    }

    def update(Long id) {
        if (request.method != "PUT") {
            response.setStatus(405)
            render "Método não permitido para esta ação."
            return
        }
        try {
            def funcionarioData = request.JSON
            def api2BaseUrl = "http://localhost:8080/api2/"
            def client = new RESTClient(api2BaseUrl)
            def response = client.put(
                    path: "funcionario/update/$id",
                    requestContentType: 'application/json',
                    body: funcionarioData
            )
            def jsonResponse = new JsonBuilder()
            jsonResponse {
                data response.data
            }
            render(contentType: 'application/json', text: jsonResponse.toString())
        } catch (Exception e) {
            renderError(e.message)
        }
    }

    def delete(Long id) {
        if (request.method != "DELETE") {
            response.setStatus(405)
            render "Método não permitido para esta ação."
            return
        }
        try {
            def api2BaseUrl = "http://localhost:8080/api2/"
            def client = new RESTClient(api2BaseUrl)
            def response = client.delete(
                    path: "funcionario/delete/$id"
            )
            if (response.status == 204) {
                def jsonResponse = new JsonBuilder()
                jsonResponse {
                    message "Funcionário ${id} deletado com sucesso!"
                }
                render(contentType: 'application/json', status: 200, text: jsonResponse.toString())
            }
        } catch (Exception e) {
            def jsonResponse = new JsonBuilder()
            jsonResponse {
                message "Não é possível excluir o funcionário."
            }
            render(contentType: 'application/json', status: 200, text: jsonResponse.toString())
        }
    }
}
package br.winxbank.repository;

import br.winxbank.sistemabancario.*;
import br.winxbank.sistemaclientes.Cliente;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import br.winxbank.sistemaclientes.RegistroDeClientes;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import kong.unirest.JsonNode;
import kong.unirest.ObjectMapper;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.json.simple.parser.*;

/**
 * @author Natália
 * Classe responsável por persistir dados de clientes em um arquivo.
 */
public class ArquivoDeClientes {

    private static ArquivoDeClientes instancia;

    public void readjason() throws IOException {
        JsonElement json = null;
        ArrayList<Cliente> clientes  = new ArrayList<>();
        try (Reader reader = new InputStreamReader(new FileInputStream("clientes.json"), "UTF-8")) {
            json = JsonParser.parseReader(reader);
            System.out.println(json);
            JSONArray array = new JSONArray(json.toString());
            for(int i=0; i < array.length(); i++)
            {
                JSONObject object = array.getJSONObject(i);
                Cliente cliente = new Cliente(object.getString("nome"), object.getString("cpf"));
                System.out.println("Cliente criado: "+ cliente.getNome() + cliente.getCpf()); //print debug
                JSONArray array2;
                System.out.println("Array de contas do cliente atual: " + object.getJSONArray("contas")); //print debug
                array2 = object.getJSONArray("contas");
                for(int j = 0; j < array2.length(); i++){
                    JSONObject object2 = array2.getJSONObject(i);
                    System.out.println(object2); //print debug
                    System.out.println(object2.getString("numeroConta")); //print debug
                    JSONArray array3;
                    System.out.println("Extrato da conta: " + object2.getJSONArray("extrato")); //print debug
                    JSONObject cartaoCreditoJson;
                    cartaoCreditoJson = object2.getJSONObject("cartaoCredito");
                    System.out.println(cartaoCreditoJson); //print debug
                    JSONObject cartaoDebitoJson;
                    cartaoDebitoJson = object2.getJSONObject("cartao");
                    System.out.println(cartaoDebitoJson);
                    array3 = object2.getJSONArray("extrato");
                    Conta contaAtual;
                    System.out.println(cartaoCreditoJson != null);
                    //PROBLEMA ROLANDO NESSES IFS
                    if(cartaoCreditoJson != null){
                        contaAtual = new ContaCorrente(object2.getInt("numeroConta"), object2.getDouble("saldo"), new Cartao(cartaoDebitoJson.getInt("numero"), cartaoDebitoJson.getInt("csv")), object2.getDouble("dividaEmprestimo"), new CartaoCredito(cartaoCreditoJson.getDouble("fatura"), cartaoCreditoJson.getInt("indexMesDaFatura"), cartaoCreditoJson.getBoolean("faturaPaga"), cartaoCreditoJson.getDouble("limite"), cartaoCreditoJson.getInt("numero"), cartaoCreditoJson.getInt("csv")));
                        System.out.println("Conta corrente foi criada");
                    }else{
                        contaAtual = new ContaPoupanca(object2.getInt("numeroConta"), object2.getDouble("saldo"), new Cartao(cartaoDebitoJson.getInt("numero"), cartaoDebitoJson.getInt("csv")), object2.getDouble("dividaEmprestimo"));
                        System.out.println("Conta poupanca foi criada");
                    }
                    cliente.setContas(contaAtual);
                    for(int x = 0; x < array3.length(); x++){
                        JSONObject object3 = array3.getJSONObject(i);
                        System.out.println(object3.getString("mesAtual"));
                        Movimentacao movimentacao = new Movimentacao(object3.getString("mesAtual"), object3.getDouble("dinheiroMovimentado"), object3.getString("tipoDaMovimentacao"));
                        contaAtual.setExtrato(movimentacao);
                    }

                }
                clientes.add(cliente);
            }
            //print debug
            for(Cliente cliente : clientes){
                System.out.println(cliente);
            }
            RegistroDeClientes.getInstancia().setClientes(clientes);

        } catch (Exception e) {
            // do something
        }
    }

    public void escreverJson(ArrayList<Cliente> clientes) throws IOException {
        try (Writer writer = new FileWriter("clientes.json")) {
            Gson gson = new GsonBuilder().create();
            JSONArray jsonArray = new JSONArray();
            JSONArray jsonArray2 = new JSONArray();
            for(Cliente cliente : clientes){
                JSONObject cliente2 = new JSONObject();
                cliente2.put("nome", cliente.getNome());
                cliente2.put("cpf", cliente.getCpf());
                JSONArray contas = new JSONArray(cliente.getContas());
                cliente2.put("contas", contas);
                jsonArray.put(cliente2);

            }
            //gson.toJson(jsonArray, writer);

            writer.write(jsonArray.toString());
        }
    }
    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static ArquivoDeClientes getInstancia() {
        if (instancia == null) {
            instancia = new ArquivoDeClientes();
        }
        return instancia;
    }

}

/*
 * Copyright (c) 2018, Michel Graciano.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the project's authors nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS AND/OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package br.com.javanoroeste.cdi.select.impostos;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/base-calculo-cdi")
public class BaseCalculoCdiResource {

    @Inject
    @Any
    private Instance<BaseCalculoService> calculos;

    @GET
    @Produces("application/json")
    public BaseCalculo get(@QueryParam("uf") Uf uf) {
        // Desta forma, podemos selecionar dinamicamente o comportamente da aplicação baseado em quaisquer
        // dados disponpiveis!!! Desde enums a quaisquer tipos de literais como strings, números, etc.
        final Instance<BaseCalculoService> instancia = calculos.select(new BaseCalculoPraQualifier(uf));
        if (instancia.isUnsatisfied()) {
            throw new UnsupportedOperationException("Operação ainda não implementada para " + uf.getNome());
        } else if (instancia.isAmbiguous()) {
            throw new IllegalStateException("Mais de uma implementação foi encontrada para " + uf.getNome());
        }

        return instancia.get().calcula();
    }
}

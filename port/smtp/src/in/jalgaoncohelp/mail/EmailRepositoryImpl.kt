
/*
 * MIT License
 *
 * Copyright (c) 2021 Jalgaon CoHelp
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package `in`.jalgaoncohelp.mail

import `in`.jalgaoncohelp.core.mail.EmailRepository
import java.util.Properties
import java.util.logging.Logger
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class EmailRepositoryImpl(
    private val emailConfig: EmailConfig,
    private val scope: CoroutineScope
) : EmailRepository {

    private val properties = Properties().apply {
        put("mail.smtp.host", emailConfig.host)
        put("mail.smtp.socketFactory.port", emailConfig.port)
        put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        put("mail.smtp.auth", "true")
        put("mail.smtp.port", emailConfig.port)
    }

    private val session = Session.getDefaultInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(emailConfig.email, emailConfig.password)
        }
    })

    override fun sendEmail(to: String, subject: String, body: String) {
        try {
            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(emailConfig.email, emailConfig.senderName))
                addRecipient(Message.RecipientType.TO, InternetAddress(to))
                setSubject(subject)
                setContent(body, "text/html")
            }

            scope.launch { Transport.send(message) }
        } catch (e: Exception) {
            Logger.getLogger(javaClass.simpleName).info(e.message)
        }
    }
}

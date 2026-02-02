const { mdToPdf } = require('md-to-pdf');
const path = require('path');

(async () => {
    try {
        const inputPath = path.resolve(__dirname, '../res/design_document.md');
        const outputPath = path.resolve(__dirname, '../res/design_document.pdf');

        console.log(`Converting ${inputPath} to PDF...`);

        await mdToPdf(
            { path: inputPath },
            {
                dest: outputPath,
                stylesheet_encoding: 'utf-8',
                pdf_options: {
                    format: 'A4',
                    margin: '20mm',
                    printBackground: true,
                },
                css: `
                    body { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; }
                    .mermaid { display: flex; justify-content: center; margin: 20px 0; }
                    pre.mermaid-pre { background: none; border: none; }
                `,
                script: [
                    {
                        content: `
                        const mermaidScript = document.createElement('script');
                        mermaidScript.src = 'https://cdn.jsdelivr.net/npm/mermaid@10/dist/mermaid.min.js';
                        mermaidScript.onload = () => {
                            mermaid.initialize({ startOnLoad: true, theme: 'default' });
                            // Find code blocks marked as mermaid and convert them
                            document.querySelectorAll('code.language-mermaid').forEach(el => {
                                const pre = el.parentElement;
                                pre.classList.add('mermaid-pre');
                                const div = document.createElement('div');
                                div.classList.add('mermaid');
                                div.textContent = el.textContent;
                                pre.replaceWith(div);
                            });
                            mermaid.run();
                        };
                        document.head.appendChild(mermaidScript);
                    ` }
                ],
                launch_options: {
                    args: ['--no-sandbox']
                }
            }
        );

        console.log(`✅ PDF created successfully at: ${outputPath}`);
    } catch (err) {
        console.error('❌ PDF generation failed:', err);
    }
})();

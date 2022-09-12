import { render, screen } from '@testing-library/react';
import Portfolio from './Portfolio';

test('renders expected text', () => {
  const rendered = render(<Portfolio />);
  const loadingElement = screen.getByText(/loading/i);
  expect(loadingElement).toBeInTheDocument();
});
